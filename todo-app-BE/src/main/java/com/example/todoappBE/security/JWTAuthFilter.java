package com.example.todoappBE.security;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.todoappBE.entities.User;
import com.example.todoappBE.exceptions.NoTokenException;
import com.example.todoappBE.services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

	@Autowired
	JwtTools jwttools;
	@Autowired
	UserService usersSrv;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, NoTokenException {
		try {
			String authHeader = request.getHeader("Authorization");

			if (authHeader == null || !authHeader.startsWith("Bearer "))
				throw new NoTokenException("Per favore passa il token nell'authorization header");
			String token = authHeader.substring(7);
			System.err.println("TOKEN -------> " + token);
			System.err.println("**********");
			// 2. Verifico che il token non sia nè scaduto nè sia stato manipolato
			jwttools.verifyToken(token);
			// 3. Se è tutto OK

			// 3.1 Cerco l'utente tramite id (l'id sta nel token quindi devo estrarlo da lì)
			String id = jwttools.extractSubject(token);
			System.err.println("Id from token: " + id);
			System.err.println("**********");

			User currentUser = usersSrv.findById(UUID.fromString(id));
			System.err.println("User: " + currentUser);
			System.err.println("**********");

			// 3.2 Segnaliamo a Spring Security che l'utente ha il permesso di procedere
			// Se non facciamo questa procedura, ci verrà tornato 403

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(currentUser, null,
					currentUser.getAuthorities());
			System.err.println("AuthToken: " + authToken.toString());
			System.err.println("**********");

			SecurityContextHolder.getContext().setAuthentication(authToken);
			// 3.3 Puoi procedere al prossimo blocco della filter chain
			filterChain.doFilter(request, response);

			// 4. Se non è OK --> 401 ("Per favore effettua di nuovo il login")
		} catch (NoTokenException e) {
			throw new NoTokenException(e.getMessage());
		}

	}

//	@Override
//	protected boolean shouldNotFilter(HttpServletRequest request) {
//		// Serve per bypassare questo filtro per alcune richieste (tipo tutte quelle su
//		// /auth/** o /users/**)
//		return !(new AntPathMatcher().match("/auth/**", request.getServletPath())
//				|| new AntPathMatcher().match("/users/**", request.getServletPath()));
//	}
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		System.out.println(request.getServletPath());
		// Serve per bypassare questo filtro per alcune richieste (tipo tutte quelle su
		// /auth/**)
		return new AntPathMatcher().match("/auth/**", request.getServletPath());
	}


}

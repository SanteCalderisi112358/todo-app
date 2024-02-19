package com.example.todoappBE.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.todoappBE.entities.User;
import com.example.todoappBE.exceptions.UnauthorizedException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTools {
	@Value("${spring.jwt.secret}")
	private String secret;

	public String createToken(User utente) {
		String token = Jwts.builder().setSubject(utente.getId().toString()) // A chi appartiene il token (Subject)
				.setIssuedAt(new Date(System.currentTimeMillis())) // Quando è stato emesso il token (IAT - Issued At)
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // Quando scadrà il
				// token
				.signWith(Keys.hmacShaKeyFor(secret.getBytes())) // Genero la firma del token
				.compact(); // Crea il token con quanto detto prima
		return token;
	}

	public void verifyToken(String token) throws UnauthorizedException {
		try {
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
			System.err.println("Token verificato");

		} catch (UnauthorizedException e) {
			System.out.println(e.getMessage());
			throw new UnauthorizedException("Il token non è valido! Per favore effettua di nuovo il login");
		}
	}

	public String extractSubject(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token)
				.getBody().getSubject();
		// Nel body (payload) ci sono il subject ("sub"), la data di emissione ("iat") e
		// la data di scadenza ("exp")
		// Nel nostro caso il subject è l'ID dell'utente
	}
}

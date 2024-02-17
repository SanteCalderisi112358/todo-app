package com.example.todoappBE.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoappBE.entities.User;
import com.example.todoappBE.exceptions.NotFoundException;
import com.example.todoappBE.exceptions.UnauthorizedException;
import com.example.todoappBE.payloads.LoginSuccessfullPayload;
import com.example.todoappBE.payloads.UserLoginPayload;
import com.example.todoappBE.payloads.UserRequestBody;
import com.example.todoappBE.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	UserService userSrv;
	@Autowired
	JwtTools jwtTools;

	@Autowired
	PasswordEncoder bcrypt;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public User saveUser(@RequestBody @Validated UserRequestBody body) {
		body.setPassword(bcrypt.encode(body.getPassword()));
		// body.setCreditCard("1234123412341234");
		User created = userSrv.createUser(body.getNome(), body.getCognome(), body.getEmail(), body.getPassword());

		return created;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginSuccessfullPayload> login(@RequestBody UserLoginPayload body)
			throws UnauthorizedException, NotFoundException {
		System.err.println("Email: " + body.getEmail());
		System.err.println("Password: " + body.getPassword());
		User user = userSrv.findByEmail(body.getEmail());
		System.err.println(user.toString());
		System.err.println(user.getPassword());
		if (user == null) {

			throw new NotFoundException("Utente non trovato");
		} else {
			if (bcrypt.matches(body.getPassword(), user.getPassword())) {

				String token = jwtTools.createToken(user);
				User utente = user;

				LoginSuccessfullPayload loginAvvenuto = new LoginSuccessfullPayload(token, utente);
				return new ResponseEntity<>(loginAvvenuto, HttpStatus.OK);

			} else {

				throw new UnauthorizedException("Credenziali non valide!");
			}
		}

	}
}

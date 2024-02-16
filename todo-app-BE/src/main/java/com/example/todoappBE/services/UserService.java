package com.example.todoappBE.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todoappBE.entities.User;
import com.example.todoappBE.exceptions.BadRequestException;
import com.example.todoappBE.repositories.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepo;

	@Autowired
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public User createUser(String name, String last_name, String email, String password) {
		// Controlla se l'email è già stata utilizzata
		if (userRepo.findByEmail(email).isPresent()) {
			throw new BadRequestException("L'email è già stata utilizzata");
		}

		// Crea un nuovo utente
		User newUser = new User(name, last_name, email, password);

		// Salva il nuovo utente nel repository
		return userRepo.save(newUser);
	}

}

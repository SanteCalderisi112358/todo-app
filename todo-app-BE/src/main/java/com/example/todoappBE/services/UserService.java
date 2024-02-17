package com.example.todoappBE.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.todoappBE.entities.User;
import com.example.todoappBE.exceptions.BadRequestException;
import com.example.todoappBE.exceptions.NotFoundException;
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

	public List<User> getAllUser() {
		return userRepo.findAll();
	}

	public Page<User> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return userRepo.findAll(pageable);
	}

	public User findByEmail(String email) {
		return userRepo.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));
	}

	public User findById(UUID id) throws NotFoundException {
		return userRepo.findById(id)
				.orElseThrow(() -> new NotFoundException("L'utente con id: " + id + " non è stato trovato"));
	}
}

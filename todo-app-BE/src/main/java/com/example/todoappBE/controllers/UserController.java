package com.example.todoappBE.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoappBE.entities.User;
import com.example.todoappBE.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userSrv;

//	@GetMapping
//	public Page<User> getUtenti(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
//			@RequestParam(defaultValue = "id") String sortBy) {
//		return userSrv.find(page, size, sortBy);
//	}

	@GetMapping
	public List<User> getUsers() {
		return userSrv.getAllUser();
	}


}

package com.example.todoappBE;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.todoappBE.entities.User;
import com.example.todoappBE.services.TodoService;
import com.example.todoappBE.services.UserService;
import com.github.javafaker.Faker;

@Component
public class MainRunner implements CommandLineRunner {
	@Autowired
	UserService userSrv;
	@Autowired
	TodoService todoSrv;
	@Override
	public void run(String... args) throws Exception {

		Faker faker = new Faker(Locale.ITALIAN);

//		for (int i = 0; i < 40; i++) {
//			User user = new User(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(),
//				faker.crypto().md5());
//		System.out.println(user);
//		userSrv.createUser(user.getName(), user.getLast_name(), user.getEmail(), user.getPassword());
//	}
		List<User> allUser = userSrv.getAllUser();
//		allUser.forEach(user -> System.err.println(user));

//		todoSrv.createTodo("Andare dal dermatologo", 2, new Date(), false, new Date(), allUser.get(3));

		User user = allUser.get(0);
		System.err.println(user);
//		todoSrv.createTodo("Andare dal commercialista", 2, new Date(), false, new Date(), user);
		todoSrv.updateTodo(UUID.fromString("fd61c6dd-9bf5-4fe8-a88d-1164dad13aa3"), "Andare dal medico", 200,
				new Date(), false);
	}

}

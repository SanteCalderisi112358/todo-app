package com.example.todoappBE;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.todoappBE.entities.User;
import com.example.todoappBE.services.UserService;
import com.github.javafaker.Faker;

@Component
public class MainRunner implements CommandLineRunner {
	@Autowired
	UserService userSrv;

	@Override
	public void run(String... args) throws Exception {

		Faker faker = new Faker(Locale.ITALIAN);

		User user = new User(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(),
				faker.crypto().md5());
		System.out.println(user);
		userSrv.createUser(user.getName(), user.getLast_name(), user.getEmail(), user.getPassword());
	}

}

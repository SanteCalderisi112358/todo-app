package com.example.todoappBE;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
//		userSrv.createUser(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(),
//				faker.crypto().md5());
//		userSrv.createUser(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(),
//				faker.crypto().md5());
//		userSrv.createUser(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(),
//				faker.crypto().md5());
//		List<User> allUser = userSrv.getAllUser();
//		User user = allUser.get(0);
//		todoSrv.createTodo("Pisciare", 2, new Date(), false, new Date(), user);
//		todoSrv.createTodo("Cacare", 1, new Date(), false, new Date(), user);
//		todoSrv.createTodo("Cucinare", 3, new Date(), false, new Date(), user);
//		todoSrv.createTodo("Morire", 2, new Date(), false, new Date(), user);
//		List<Todo> allUsersOf = todoSrv.getTodosByIdUser(UUID.fromString("c671c2da-3f6f-4079-838e-84b94b659471"));
//		allUsersOf.forEach(t -> System.err.println(t));

	}

}

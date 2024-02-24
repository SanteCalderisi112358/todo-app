package com.example.todoappBE.controllers;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoappBE.entities.Todo;
import com.example.todoappBE.exceptions.BadRequestException;
import com.example.todoappBE.exceptions.NoTokenException;
import com.example.todoappBE.exceptions.NotFoundException;
import com.example.todoappBE.exceptions.UnauthorizedException;
import com.example.todoappBE.payloads.TodoRequestBody;
import com.example.todoappBE.security.JwtTools;
import com.example.todoappBE.services.TodoService;
import com.example.todoappBE.services.UserService;

@RestController
@RequestMapping("/todos")
public class TodoController {
	@Autowired
	TodoService todoSrv;

	@Autowired
	UserService userSrv;

	@Autowired
	JwtTools jwttools;

	@GetMapping("/user={userId}")
	public List<Todo> getTodosByUtente(@PathVariable UUID userId)
			throws UnauthorizedException, BadRequestException, NoTokenException {
		try {

			List<Todo> todos = todoSrv.getTodosByIdUser(userId);
			if (!todos.isEmpty()) {
				return todos;
			} else {
				throw new BadRequestException("La tua todo list è vuota");
			}
		} catch (UnauthorizedException | BadRequestException e) {
			throw e;
		}
	}

	@GetMapping("/userId={userId}&todoId={todoId}")
	public Todo getTodoById(@PathVariable UUID userId, @PathVariable UUID todoId) {

		return todoSrv.findTodoById(userId, todoId);
	}

	@PostMapping("/userId={userId}")
	public Todo createTodo(@RequestBody @Validated TodoRequestBody body,
			@PathVariable UUID userId) {
		try {

			body.setCreated_at(new Date());
			body.setCompleted(false);
			return todoSrv.createTodo(body);
		} catch (UnauthorizedException e) {
			throw new UnauthorizedException(e.getMessage());
		}
	}



		
		
		
		
	@PutMapping("?userId={userId}&todoId={todoId}")
	public Todo updateTodo(@PathVariable UUID userId, @PathVariable UUID todoId,
			@RequestBody @Validated TodoRequestBody body) throws UnauthorizedException {
		try {

		String newDescription = body.getDescription();
		int updatePriotiry = body.getPriority();
		Date updateDeadline = body.getDeadline();
		boolean updateCompleted = body.isCompleted();
		UUID userIdPut = userId;
		return todoSrv.updateTodo(todoId, newDescription, updatePriotiry, updateDeadline, updateCompleted, userIdPut);
	} catch (UnauthorizedException e) {
		throw new UnauthorizedException(e.getMessage());
	}

	}

	@DeleteMapping("userId={userId}&todoId={todoId}")
	public void deleteTodo(@PathVariable UUID userId, @PathVariable UUID todoId)
			throws UnauthorizedException, NotFoundException {
		try {
			todoSrv.deleTodo(todoId, userId);
		} catch (UnauthorizedException e) {
			throw new UnauthorizedException(e.getMessage());
		}

	}
}

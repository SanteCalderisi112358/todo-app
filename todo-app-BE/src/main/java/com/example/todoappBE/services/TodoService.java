package com.example.todoappBE.services;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todoappBE.entities.Todo;
import com.example.todoappBE.entities.User;
import com.example.todoappBE.exceptions.TodoNotFoundException;
import com.example.todoappBE.repositories.TodoRepository;

@Service
public class TodoService {
	private final TodoRepository todoRepo;

	@Autowired
	public TodoService(TodoRepository todoRepo) {
		this.todoRepo = todoRepo;
	}

	public Todo createTodo(String description, int priority, Date deadline, boolean completed, Date create_at,
			User user) {
		Todo newTodo = new Todo(description, priority, deadline, completed, create_at, user);
		return todoRepo.save(newTodo);
	}
	
	public Todo updateTodo(UUID idTodo, String newDescription, int newPriority, Date newDeadline,
			boolean newCompleted, UUID idUser) {
		Optional<Todo> todoOptional = todoRepo.findById(idTodo);
		Todo todo = todoOptional.orElseThrow(() -> new TodoNotFoundException("Todo non trovato per l'id: " + idTodo));
		if (!todo.getUser().getId().equals(idUser)) {
			throw new TodoNotFoundException("Non sei autorizzato!");
		}
		todo.setCompleted(newCompleted);
		todo.setDeadline(newDeadline);
		todo.setDescription(newDescription);
		todo.setPriority(newPriority);
		return todoRepo.save(todo);

	}
	
	public void deleTodo(UUID idTodo, UUID idUser) {
		Optional<Todo> todoOptional = todoRepo.findById(idTodo);
		Todo todo = todoOptional.orElseThrow(() -> new TodoNotFoundException("Todo non trovato per l'id: " + idTodo));
		if (!todo.getUser().getId().equals(idUser)) {
			throw new TodoNotFoundException("Non sei autorizzato!");
		}
		todoRepo.delete(todo);
	}


}

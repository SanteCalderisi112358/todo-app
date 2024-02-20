package com.example.todoappBE.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todoappBE.entities.Todo;
import com.example.todoappBE.exceptions.NotFoundException;
import com.example.todoappBE.payloads.TodoRequestBody;
import com.example.todoappBE.repositories.TodoRepository;

@Service
public class TodoService {
	private final TodoRepository todoRepo;

	@Autowired
	public TodoService(TodoRepository todoRepo) {
		this.todoRepo = todoRepo;
	}

	public Todo createTodo(TodoRequestBody body) {
		Todo newTodo = new Todo(body.getDescription(), body.getPriority(), body.getDeadline(), body.isCompleted(),
				body.getCreated_at(), body.getUser().getUser());
		return todoRepo.save(newTodo);
	}
	
	public Todo updateTodo(UUID idTodo, String newDescription, int newPriority, Date newDeadline,
			boolean newCompleted, UUID idUser) {
		Optional<Todo> todoOptional = todoRepo.findById(idTodo);
		Todo todo = todoOptional.orElseThrow(() -> new NotFoundException("Todo non trovato per l'id: " + idTodo));

		todo.setCompleted(newCompleted);
		todo.setDeadline(newDeadline);
		todo.setDescription(newDescription);
		todo.setPriority(newPriority);
		return todoRepo.save(todo);

	}
	
	public void deleTodo(UUID idTodo, UUID idUser) throws NotFoundException {
		Optional<Todo> todoOptional = todoRepo.findById(idTodo);
		Todo todo = todoOptional.orElseThrow(() -> new NotFoundException("Todo non trovato per l'id: " + idTodo));
		todoRepo.delete(todo);
	}

	public List<Todo> getTodosByIdUser(UUID idUser) {

		return todoRepo.getTodosByUserId(idUser);
	}

	public Todo findTodoById(UUID userId, UUID todoId) throws NotFoundException {
		return todoRepo.getTodoByTodoId(userId, todoId);
	}
}

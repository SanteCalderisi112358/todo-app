package com.example.todoappBE.controllers;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoappBE.entities.Todo;
import com.example.todoappBE.exceptions.BadRequestException;
import com.example.todoappBE.exceptions.UnauthorizedException;
import com.example.todoappBE.payloads.LoginSuccessfullPayload;
import com.example.todoappBE.payloads.TodoRequestBody;
import com.example.todoappBE.security.JwtTools;
import com.example.todoappBE.services.TodoService;
import com.example.todoappBE.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

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
	public List<Todo> getTodosByUtente(@PathVariable UUID userId, @RequestBody @Validated LoginSuccessfullPayload body,
			HttpServletRequest request) throws UnauthorizedException, BadRequestException {
		try {
			String authHeader = request.getHeader("Authorization");
			String tokenInAuth = authHeader.substring(7);
			String accessToken = body.getAccessToken();
			if (!tokenInAuth.equals(accessToken)) {
				throw new UnauthorizedException("Non sei autorizzato!");
			}

			// Ora hai confermato che il token JWT è valido e puoi procedere con la logica
			// della tua applicazione
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


	@PostMapping
	public Todo createTodo(@RequestBody @Validated TodoRequestBody body, HttpServletRequest request) {
		try {
			String token = body.getUser().getAccessToken();
			String authHeader = request.getHeader("Authorization");
			String tokenInAuth = authHeader.substring(7);

			if (!token.equals(tokenInAuth))
				throw new UnauthorizedException("Non sei autorizzato!");

			body.setCreated_at(new Date());
			body.setCompleted(false);
			return todoSrv.createTodo(body);
		} catch (UnauthorizedException e) {
			throw new UnauthorizedException(e.getMessage());
		}
	}



		
		
		
		
	@PutMapping("userId={userId}/todoId={todoId}")
	public String updateTodo(@PathVariable UUID todoId, @PathVariable UUID userId, @RequestBody TodoRequestBody body) {
		return userId + " " + todoId;
	}

//	@DeleteMapping("/{donazioneId}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	@PreAuthorize("hasAuthority('ADMIN')")
//	public void deleteDonazione(@PathVariable UUID donazioneId) {
//		donazioneSrv.findByIdAndDelete(donazioneId);
//	}

}

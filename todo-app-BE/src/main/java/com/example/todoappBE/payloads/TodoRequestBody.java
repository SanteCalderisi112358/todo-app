package com.example.todoappBE.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TodoRequestBody {
	private String description;
	private int priority;
	private Date deadline;
	private boolean completed;
	private Date created_at;
	private LoginSuccessfullPayload user;

	public TodoRequestBody() {

	}

	public TodoRequestBody(String description, int priority, Date deadline,
			LoginSuccessfullPayload user) {
		this.description = description;
		this.priority = priority;
		this.deadline = deadline;
		this.user = user;
	}
}

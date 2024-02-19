package com.example.todoappBE.payloads;

import com.example.todoappBE.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginSuccessfullPayload {
	private String accessToken;
	private User user;
}

package com.example.todoappBE.exceptions;

public class TodoNotFoundException extends RuntimeException {
public TodoNotFoundException(String message) {
	super(message);
}
}

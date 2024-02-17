package com.example.todoappBE.payloads;

public class UserLoginPayload {
	String email;
	String password;

	public UserLoginPayload(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserLoginPayload [email=" + email + ", password=" + password + "]";
	}

}

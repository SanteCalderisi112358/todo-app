package com.example.todoappBE.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "utenti")
@NoArgsConstructor
@JsonIgnoreProperties({
		// "password",
		"accountNonExpired", "authorities", "credentialsNonExpired", "accountNonLocked", "username", "enabled" })
public class User {
	@Id
	@GeneratedValue
	private UUID id;
	private String name;
	private String last_name;
	@Column(nullable = false, unique = true)
	private String email;
	private String password;

	public User(String name, String last_name, String email, String password) {
		this.name = name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", last_name=" + last_name + ", email=" + email + ", password="
				+ password + "]";
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

}

package com.example.todoappBE.entities;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "todo list")
public class Todo {
	@Id
	@GeneratedValue
	private UUID id;
	private String description;
	private int priority;
	private Date deadline;
	private boolean completed;
	private Date created_at;
	@ManyToOne
	private User user;

	public Todo(String description, int priority, Date deadline, boolean completed, Date created_at, User user) {
		this.description = description;
		this.priority = priority;
		this.deadline = deadline;
		this.completed = completed;
		this.created_at = created_at;
		this.user = user;
	}

	public UUID getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public int getPriority() {
		return priority;
	}

	public Date getDeadline() {
		return deadline;
	}

	public boolean isCompleted() {
		return completed;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", description=" + description + ", priority=" + priority + ", deadline=" + deadline
				+ ", completed=" + completed + ", created_at=" + created_at + ", user=" + user + "]";
	}
}

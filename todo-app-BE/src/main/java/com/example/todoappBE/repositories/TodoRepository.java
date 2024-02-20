package com.example.todoappBE.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.todoappBE.entities.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID> {

	@Query("SELECT t FROM Todo t JOIN User u ON t.user.id = u.id WHERE u.id = :userId")
	List<Todo> getTodosByUserId(@Param("userId") UUID userId);

	@Query("SELECT t FROM Todo t JOIN User u ON t.user.id = u.id WHERE u.id = :userId AND t.id = :todoId")
	Todo getTodoByTodoId(@Param("userId") UUID userId, @Param("todoId") UUID todoId);
}

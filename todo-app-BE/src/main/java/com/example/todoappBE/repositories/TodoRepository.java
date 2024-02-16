package com.example.todoappBE.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todoappBE.entities.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID> {

}

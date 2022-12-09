package de.springboot.apptodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.springboot.apptodo.entity.TodoItem;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

}

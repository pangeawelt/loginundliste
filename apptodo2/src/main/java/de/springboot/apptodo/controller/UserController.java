package de.springboot.apptodo.controller;

import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.springboot.apptodo.entity.TodoItem;
import de.springboot.apptodo.entity.User;
import de.springboot.apptodo.repository.TodoItemRepository;
import de.springboot.apptodo.repository.UserRepository;
import de.springboot.apptodo.request.AddTodoItemRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserRepository userRepository;
	private final TodoItemRepository todoItemRepository;

	@GetMapping("/{userId}")
	public User getUserById(@PathVariable Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
	}

	@PostMapping("/{userId}/todos")
	public void addTodoItem(@PathVariable Long userId, @RequestBody AddTodoItemRequest todoItemRequest) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
		TodoItem todoItem = new TodoItem();
		todoItem.setDescription(todoItemRequest.getDescription());
		user.getTodoItems().add(todoItem);
		todoItemRepository.save(todoItem);
		userRepository.save(user);
	}

	@PostMapping("/todos/{todoItemId}")
	public void toggleTodoItemCompleted(@PathVariable Long todoItemId) {
		TodoItem todoItem = todoItemRepository.findById(todoItemId).orElseThrow(() -> new NoSuchElementException());
		todoItem.setCompleted(!todoItem.getCompleted());
		todoItemRepository.save(todoItem);
	}

	@DeleteMapping("{userId}/todos/{todoItemId}")
	public void deleteTodo(@PathVariable Long userId, @PathVariable Long todoItemId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
		TodoItem todoItem = todoItemRepository.findById(todoItemId).orElseThrow(() -> new NoSuchElementException());
		user.getTodoItems().remove(todoItem);
		todoItemRepository.delete(todoItem);
	}

	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
		userRepository.delete(user);
	}

}

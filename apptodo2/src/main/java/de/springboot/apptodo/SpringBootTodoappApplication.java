package de.springboot.apptodo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import de.springboot.apptodo.entity.TodoItem;
import de.springboot.apptodo.entity.User;
import de.springboot.apptodo.repository.TodoItemRepository;
import de.springboot.apptodo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RequiredArgsConstructor
@EnableWebMvc
@EnableSwagger2
public class SpringBootTodoappApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final TodoItemRepository todoItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTodoappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user = new User();
		user.setId(1L);
		user.setFirstName("Gani");
		user.setLastName("Buer");
		user.setEmail("gani@test.de");
		user.setPassword("password");

		TodoItem todoItem = new TodoItem();
		todoItem.setId(1L);
		todoItem.setDescription("Anfang Todo-App");
		todoItem.getCompleted();

		user.getTodoItems().add(todoItem);

		todoItemRepository.save(todoItem);
		userRepository.save(user);

	}
}
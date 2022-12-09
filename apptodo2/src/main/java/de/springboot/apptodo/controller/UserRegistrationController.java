package de.springboot.apptodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.springboot.apptodo.request.UserRegistrationRequest;
import de.springboot.apptodo.userservice.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class UserRegistrationController {

	private final UserService userService;

	@GetMapping
	public String showRegistration(Model model) {
		model.addAttribute("user", new UserRegistrationRequest());
		return "registration";
	}

	@PostMapping
	public String registerUser(@ModelAttribute("user") UserRegistrationRequest userRegistrationRequest) {
		userService.save(userRegistrationRequest);
		return "redirect:/registration?success";
	}
}
package de.springboot.apptodo.userservice;

import org.springframework.security.core.userdetails.UserDetailsService;

import de.springboot.apptodo.entity.User;
import de.springboot.apptodo.request.UserRegistrationRequest;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationRequest userRegistrationRequest);
}
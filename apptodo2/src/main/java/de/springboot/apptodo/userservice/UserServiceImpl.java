package de.springboot.apptodo.userservice;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import de.springboot.apptodo.entity.Role;
import de.springboot.apptodo.entity.User;
import de.springboot.apptodo.repository.UserRepository;
import de.springboot.apptodo.request.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	public User save(UserRegistrationRequest userRegistrationRequest) {
		User user = new User();
		user.setFirstName(userRegistrationRequest.getFirstName());
		user.setLastName(userRegistrationRequest.getLastName());
		user.setEmail(userRegistrationRequest.getEmail());
		user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
		user.setRoles(Arrays.asList(new Role("ROLE_USER")));
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("Ungiltig Benutzer Name oder Passwort!");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
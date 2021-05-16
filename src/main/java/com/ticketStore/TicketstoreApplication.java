package com.ticketStore;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ticketStore.domain.User;
import com.ticketStore.domain.security.Role;
import com.ticketStore.domain.security.UserRole;
import com.ticketStore.service.UserService;
import com.ticketStore.utils.SecurityUtils;

@SpringBootApplication
public class TicketstoreApplication implements CommandLineRunner{
	
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(TicketstoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setFirstName("John");
		user1.setLastName("Doe");
		user1.setUsername("jdoe");
		user1.setPassword(SecurityUtils.passwordEncoder().encode("pass"));
		user1.setEmail("jdoe@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
	}
}

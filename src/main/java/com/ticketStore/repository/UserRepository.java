package com.ticketStore.repository;

import org.springframework.data.repository.CrudRepository;

import com.ticketStore.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	
	User findByEmail(String email);
}

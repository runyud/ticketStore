package com.ticketStore.repository;

import org.springframework.data.repository.CrudRepository;

import com.ticketStore.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByname(String name);
}

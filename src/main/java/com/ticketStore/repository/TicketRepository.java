package com.ticketStore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ticketStore.domain.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

	List<Ticket> findByCategory(String category);
	
	// spring elastic search
	List<Ticket> findByTitleContaining(String title);

}

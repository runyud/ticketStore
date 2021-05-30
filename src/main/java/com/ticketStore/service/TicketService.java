package com.ticketStore.service;

import java.util.List;

import com.ticketStore.domain.Ticket;

public interface TicketService {
	
	List<Ticket> findAll ();

	Ticket findOne(Long id);

	List<Ticket> findByCategory(String category);

	List<Ticket> fuzzySearch(String keyword);
}

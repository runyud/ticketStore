package com.ticketStore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketStore.domain.Ticket;
import com.ticketStore.repository.TicketRepository;
import com.ticketStore.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	public List<Ticket> findAll() {
		return (List<Ticket>) ticketRepository.findAll();
	}

	@Override
	public Ticket findOne(Long id) {
		return ticketRepository.findOne(id);
	}
}

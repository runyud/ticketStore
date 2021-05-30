package com.ticketStore.service.impl;

import java.util.ArrayList;
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
		List<Ticket> ticketList = (List<Ticket>) ticketRepository.findAll();
		List<Ticket> activeTicketList = new ArrayList<>(); 
		for(Ticket ticket : ticketList) {
			if(ticket.isActive()) {
				activeTicketList.add(ticket);
			}
		}
		return activeTicketList;
	}

	@Override
	public Ticket findOne(Long id) {
		return ticketRepository.findOne(id);
	}

	@Override
	public List<Ticket> findByCategory(String category) {
		List<Ticket> ticketList = ticketRepository.findByCategory(category);
		List<Ticket> activeTicketList = new ArrayList<>();
		
		for(Ticket ticket : ticketList) {
			if(ticket.isActive()) {
				activeTicketList.add(ticket);
			}
		}
		
		return activeTicketList;
	}

	@Override
	public List<Ticket> fuzzySearch(String keyword) {
		List<Ticket> ticketList = ticketRepository.findByTitleContaining(keyword);
		List<Ticket> activeTicketList = new ArrayList<>();
		
		for(Ticket ticket : ticketList) {
			if(ticket.isActive()) {
				activeTicketList.add(ticket);
			}
		}
		
		return activeTicketList;
	}
}

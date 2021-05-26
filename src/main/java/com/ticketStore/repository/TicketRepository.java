package com.ticketStore.repository;

import org.springframework.data.repository.CrudRepository;

import com.ticketStore.domain.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

}

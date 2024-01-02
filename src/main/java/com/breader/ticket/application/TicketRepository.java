package com.breader.ticket.application;

import com.breader.ticket.domain.Ticket;
import com.breader.ticket.domain.TicketId;

import java.util.Optional;

public interface TicketRepository {

    TicketId save(Ticket ticket);

    Optional<Ticket> findById(TicketId ticketId);

}

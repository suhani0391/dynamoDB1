package com.breader.ticket.application;

import com.breader.ticket.domain.Ticket;
import com.breader.ticket.domain.TicketId;
import com.breader.ticket.infrastructure.web.command.CreateTicketCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketId handle(CreateTicketCommand createTicketCommand) {
        Ticket ticket = new Ticket(TicketId.create(), createTicketCommand.eventName());
        return ticketRepository.save(ticket);
    }
}

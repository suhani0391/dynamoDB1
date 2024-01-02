package com.breader.ticket.infrastructure.web.model;

import com.breader.ticket.domain.Ticket;

import java.util.UUID;

public record TicketDto(UUID ticketId, String eventName) {

    public static TicketDto fromTicket(Ticket ticket) {
        return new TicketDto(ticket.ticketId().value(), ticket.eventName());
    }
}

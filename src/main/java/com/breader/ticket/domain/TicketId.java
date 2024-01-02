package com.breader.ticket.domain;

import java.util.UUID;

public record TicketId(UUID value) {

    public static TicketId create() {
        return new TicketId(UUID.randomUUID());
    }

    public static TicketId from(UUID value) {
        return new TicketId(value);
    }
}

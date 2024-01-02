package com.breader.ticket.infrastructure.repository;

import com.breader.ticket.domain.Ticket;
import com.breader.ticket.domain.TicketId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.UUID;

@DynamoDbBean
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDocument {

    private UUID ticketId;
    private String eventName;

    @DynamoDbPartitionKey
    public UUID getTicketId() {
        return ticketId;
    }

    public static TicketDocument fromTicket(Ticket ticket) {
        return new TicketDocument(ticket.ticketId().value(), ticket.eventName());
    }

    public Ticket toTicket() {
        return new Ticket(TicketId.from(ticketId), eventName);
    }
}

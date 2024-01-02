package com.breader.ticket.infrastructure.repository;

import com.breader.ticket.application.TicketRepository;
import com.breader.ticket.domain.Ticket;
import com.breader.ticket.domain.TicketId;
import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;

import java.util.Optional;

@RequiredArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {

    private final DynamoDbTable<TicketDocument> ticketTable;

    @Override
    public TicketId save(Ticket ticket) {
        ticketTable.putItem(TicketDocument.fromTicket(ticket));
        return ticket.ticketId();
    }

    @Override
    public Optional<Ticket> findById(TicketId ticketId) {
        Key ticketIdKey = Key.builder()
                .partitionValue(ticketId.value().toString())
                .build();
        return Optional.ofNullable(ticketTable.getItem(ticketIdKey))
                .map(TicketDocument::toTicket);
    }
}

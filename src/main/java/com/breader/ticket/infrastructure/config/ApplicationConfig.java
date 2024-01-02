package com.breader.ticket.infrastructure.config;

import com.breader.ticket.application.TicketRepository;
import com.breader.ticket.application.TicketService;
import com.breader.ticket.infrastructure.repository.TicketDocument;
import com.breader.ticket.infrastructure.repository.TicketRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ContainerCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration(proxyBeanMethods = false)
public class ApplicationConfig {

    @Bean
    @Profile("!test")
    public AwsCredentialsProvider awsCredentialsProvider() {
        return ContainerCredentialsProvider.builder().build();
    }

    @Bean
    @Profile("!test")
    public DynamoDbClient dynamoDbClient(AwsCredentialsProvider credentialsProvider) {
        return DynamoDbClient.builder()
                .credentialsProvider(credentialsProvider)
                .region(Region.EU_WEST_1)
                .build();
    }

    @Bean
    public DynamoDbTable<TicketDocument> ticketTable(DynamoDbClient dynamoDbClient, @Value("${ticket.table}") String ticketTableName) {
        DynamoDbEnhancedClient dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        TableSchema<TicketDocument> ticketDocumentSchema = TableSchema.fromBean(TicketDocument.class);
        return dynamoDbEnhancedClient.table(ticketTableName, ticketDocumentSchema);
    }

    @Bean
    public TicketRepository ticketRepository(DynamoDbTable<TicketDocument> ticketTable) {
        return new TicketRepositoryImpl(ticketTable);
    }

    @Bean
    public TicketService ticketService(TicketRepository ticketRepository) {
        return new TicketService(ticketRepository);
    }
}

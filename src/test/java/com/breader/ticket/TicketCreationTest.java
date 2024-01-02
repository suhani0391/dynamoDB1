package com.breader.ticket;

import com.breader.config.AwsConfig;
import com.breader.config.TestcontainersConfig;
import com.breader.config.TicketDbConfig;
import com.breader.ticket.infrastructure.web.command.CreateTicketCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Import({TestcontainersConfig.class, AwsConfig.class, TicketDbConfig.class})
class TicketCreationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_save_new_ticket(@Autowired MockMvc mockMvc) throws Exception {
        mockMvc.perform(post("/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CreateTicketCommand("RHCP"))))
                .andExpect(status().isCreated());
    }
}

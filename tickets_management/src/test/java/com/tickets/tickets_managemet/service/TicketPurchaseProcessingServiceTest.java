package com.tickets.tickets_managemet.service;

import com.tickets.tickets_managemet.domain.*;
import com.tickets.tickets_managemet.service.ticket.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.ArrayList;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class TicketPurchaseProcessingServiceTest {

    @Mock
    private TicketPurchaseService service;

    private static Ticket ticket;

    private static Client client;

    private static Route route;

    @BeforeAll
    public static void setup(){

        client = new Client(1L, "Fred", "Johnson", "John",
                "fred@fred.com", false, new ArrayList<>());

        route = new Route(1L, new Station(), new Station(),
                new Date(4670194458070L), 100.0, 2,
                false, new ArrayList<>());

        ticket = new Ticket(1L, route, client, 1L,
                new Date(), "NEW", false, false);
    }


    @Test
    public void whenBuyTicketShouldReturnTicketId() {

        when(service
                .buyTicket(client, route.getId()))
                .thenReturn(ticket.getId());

        Long ticket_id = service.buyTicket(client, 1L);

        assertThat(ticket.getId()).isSameAs(ticket_id);

        verify(service).buyTicket(client, route.getId());
    }

}

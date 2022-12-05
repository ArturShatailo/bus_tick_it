package com.tickets.tickets_managemet.service;

import com.tickets.tickets_managemet.domain.*;
import com.tickets.tickets_managemet.domain.dto.TicketInfo;
import com.tickets.tickets_managemet.repository.TicketRepository;
import com.tickets.tickets_managemet.service.ticket.TicketInfoService;
import com.tickets.tickets_managemet.service.ticket.TicketInfoServiceBean;
import com.tickets.tickets_managemet.util.exceptions.ticket.TicketNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.Date;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class TicketInfoServiceTest {

    @Mock
    private TicketInfoService service;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketInfoServiceBean ticketInfoServiceBean;

    private static Ticket ticket;

    @BeforeAll
    public static void setup(){

        ticket = new Ticket(1L, new Route(), new Client(), 1L,
                new Date(), "NEW", false, false);
    }


    @Test
    public void whenGetByIdShouldReturnTicket() {

        when(ticketRepository
                .findById(anyLong()))
                .thenReturn(Optional.ofNullable(ticket));

        Ticket ticketTest = ticketInfoServiceBean.getById(1L);

        assertThat(ticketTest.getId()).isSameAs(ticket.getId());
        assertThat(ticketTest.getCurrent_status()).isSameAs(ticket.getCurrent_status());

        verify(ticketRepository).findById(ticket.getId());
    }

    @Test
    public void whenUnregisteredIdShouldReturnTicketNotFoundException(){
        Exception exception = assertThrows(TicketNotFoundException.class,
                () -> ticketInfoServiceBean.getById(5L));
        assertTrue(exception.getMessage().contains("Ticket with id: 5 was not found in database"));
    }

    @Test
    public void whenGetTicketInfoShouldReturnTicketInfo() {

        TicketInfo ticketInfo =
                new TicketInfo(ticket.getRoute(), "NEW");

        when(ticketRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(ticket));

        doReturn(ticketInfo).when(service).getTicketInfo(ticket.getId());

        var ticketInfoTest = service.getTicketInfo(ticket.getId());

        assertThat(ticketInfoTest.getPayment_status()).isSameAs(ticketInfo.getPayment_status());
        assertThat(ticketInfoTest.getRoute()).isSameAs(ticketInfo.getRoute());

        verify(service).getTicketInfo(ticket.getId());
    }

}

package com.tickets.tickets_managemet.service.ticket;

import com.tickets.tickets_managemet.domain.Ticket;
import com.tickets.tickets_managemet.domain.TicketInfo;
import com.tickets.tickets_managemet.repository.TicketRepository;
import com.tickets.tickets_managemet.util.configuration.TicketConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class TicketInfoServiceBean implements TicketInfoService{

    private final TicketRepository ticketRepository;

    private final TicketConfig ticketConfig;

    @Override
    public TicketInfo getTicketInfo(Long ticket_id) {
        Ticket ticket = getById(ticket_id);
        return new TicketInfo(ticket.getRoute(), getPaymentStatus(ticket.getPayment_id()));
    }

    private String getPaymentStatus(Long paymentID) {
        String uri ="http://localhost:8083/pay/st/{id}";
        return ticketConfig.restTemplate().getForObject(uri, String.class, paymentID);
    }

    public Ticket getById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find ticket with id: " + id));
    }

}

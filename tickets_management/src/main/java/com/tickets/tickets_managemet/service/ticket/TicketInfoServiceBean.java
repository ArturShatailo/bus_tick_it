package com.tickets.tickets_managemet.service.ticket;

import com.tickets.tickets_managemet.domain.Ticket;
import com.tickets.tickets_managemet.domain.dto.TicketInfo;
import com.tickets.tickets_managemet.repository.TicketRepository;
import com.tickets.tickets_managemet.util.configuration.TicketConfig;
import com.tickets.tickets_managemet.util.exceptions.ticket.TicketNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TicketInfoServiceBean implements TicketInfoService{

    private final TicketRepository ticketRepository;

    private final TicketConfig ticketConfig;

    @Override
    public TicketInfo getTicketInfo(Long id) {
        log.info("[Ticket system] Start method getTicketInfo with id {}", id);
        Ticket ticket = getById(id);
        return new TicketInfo(ticket.getRoute(), getPaymentStatus(ticket.getPayment_id()));
    }

    private String getPaymentStatus(Long id) {
        String uri = ticketConfig.paymentSystemGetStatus();

        log.info("[Ticket system] HTTP request to uri {} with path variable id {} " +
                "will be sent to get Payment status", uri, id);

        return ticketConfig.restTemplate().getForObject(uri, String.class, id);
    }

    public Ticket getById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket with id: " + id + " was not found in database"));
    }

}

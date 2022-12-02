package com.tickets.tickets_managemet.service.ticket;

import com.tickets.tickets_managemet.domain.Ticket;
import com.tickets.tickets_managemet.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class TicketInfoServiceBean implements TicketInfoService{

    private final TicketRepository ticketRepository;

    @Override
    public Ticket getTicketInfo(Long ticket_id) {
        return getById(ticket_id);
    }

    public Ticket getById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find ticket with id: " + id));
    }

}

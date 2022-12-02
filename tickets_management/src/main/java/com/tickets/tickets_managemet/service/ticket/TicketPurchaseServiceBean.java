package com.tickets.tickets_managemet.service.ticket;

import com.tickets.clients_management.domain.Client;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TicketPurchaseServiceBean implements TicketPurchaseService{

    private final TicketPurchaseProcessingServiceBean purchaseProcessingServiceBean;

    @Override
    public Long buyTicket(Client client, Long route_id) {
        return purchaseProcessingServiceBean.ticketPurchaseProcessing(client, route_id);
    }
}

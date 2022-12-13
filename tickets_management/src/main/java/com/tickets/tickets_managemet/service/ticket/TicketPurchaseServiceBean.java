package com.tickets.tickets_managemet.service.ticket;

import com.tickets.tickets_managemet.domain.Client;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TicketPurchaseServiceBean implements TicketPurchaseService{

    //private final TicketPurchaseProcessingServiceBean purchaseProcessingServiceBean;

    private final TicketPurchaseProcessingService purchaseProcessingService;

    @Override
    public Long buyTicket(Client client, Long route_id) {
        return purchaseProcessingService.ticketPurchaseProcessing(client, route_id);
    }
}

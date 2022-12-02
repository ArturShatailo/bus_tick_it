package com.tickets.tickets_managemet.service.ticket;

import com.tickets.tickets_managemet.domain.Client;

public interface TicketPurchaseProcessingService {

    Long ticketPurchaseProcessing(Client client, Long route_id);

}

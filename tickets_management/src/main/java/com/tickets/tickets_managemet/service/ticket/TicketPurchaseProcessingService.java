package com.tickets.tickets_managemet.service.ticket;

import com.tickets.clients_management.domain.Client;

public interface TicketPurchaseProcessingService {

    Long ticketPurchaseProcessing(Client client, Long route_id);

}

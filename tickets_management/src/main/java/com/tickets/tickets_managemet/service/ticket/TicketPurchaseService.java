package com.tickets.tickets_managemet.service.ticket;

import com.tickets.clients_management.domain.Client;

public interface TicketPurchaseService {

    Long buyTicket(Client client, Long route_id);

}

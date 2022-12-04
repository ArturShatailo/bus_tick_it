package com.tickets.tickets_managemet.service.ticket;

import com.tickets.tickets_managemet.domain.Client;

public interface TicketPurchaseService {

    /**
     * Calls TicketPurchaseProcessing method of TicketPurchaseProcessingService to proceed the
     * process of Ticked trade.
     * New Ticket object will be created according to the received data from parameter Client and
     * from the Route object that is found in database by received in parameter route_id.
     * New Payment will be created according to the received data and through the HTTP request
     * to payment system service.
     * Method returns an id of the created Ticket.
     *
     * @param client   a Client object
     * @param route_id a Route id
     * @return the long
     */
    Long buyTicket(Client client, Long route_id);

}

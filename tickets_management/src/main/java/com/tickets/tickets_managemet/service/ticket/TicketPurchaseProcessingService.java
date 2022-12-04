package com.tickets.tickets_managemet.service.ticket;

import com.tickets.tickets_managemet.domain.Client;

public interface TicketPurchaseProcessingService {

    /**
     * This method implements the process of Ticked trade.
     * The Route object received from database by id from parameter route_id is checked for the availability to
     * buy a ticket.
     * New Ticket object will be created according to the received data from parameter Client and
     * from the Route object.
     * New Payment will be created according to the received data and through the HTTP request
     * to payment system service.
     * Method updates information of the Route after a ticket trade using updateSell method of RouteCrudService
     * Method returns an id of the created Ticket.
     *
     * @param client   a Client object
     * @param route_id a Route id
     * @return the id value of created Ticket
     */
    Long ticketPurchaseProcessing(Client client, Long route_id);

}

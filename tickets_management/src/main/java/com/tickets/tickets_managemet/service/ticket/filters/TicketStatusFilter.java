package com.tickets.tickets_managemet.service.ticket.filters;

import com.tickets.tickets_managemet.domain.Ticket;

public interface TicketStatusFilter {

    /**
     * Checks if the received in parameter Ticket object has status
     * equals to the received in parameter one.
     *
     * @param ticket a Ticket object
     * @param status status that should be checked
     * @return boolean true if equals
     */
    boolean ticketStatusFilter(Ticket ticket, String status);

}

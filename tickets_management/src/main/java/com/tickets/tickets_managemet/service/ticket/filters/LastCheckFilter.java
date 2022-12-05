package com.tickets.tickets_managemet.service.ticket.filters;

import com.tickets.tickets_managemet.domain.Ticket;

public interface LastCheckFilter {

    /**
     * Checks if the received in parameter Ticket object was checked more than
     * 10 seconds before this moment.
     *
     * @param ticket a Ticket object
     * @return boolean false if 10 seconds have not passed
     */
    boolean lastCheckFilter(Ticket ticket);

}

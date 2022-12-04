package com.tickets.tickets_managemet.service.ticket.filters;

import com.tickets.tickets_managemet.domain.Ticket;

public interface DeletedFilter {


    /**
     * Checks if the received in parameter Ticket object is not deleted.
     *
     * @param ticket a Ticket object
     * @return boolean false if deleted
     */
    boolean deletedFilter(Ticket ticket);

}

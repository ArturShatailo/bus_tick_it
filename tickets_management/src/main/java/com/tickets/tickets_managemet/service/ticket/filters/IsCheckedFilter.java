package com.tickets.tickets_managemet.service.ticket.filters;

import com.tickets.tickets_managemet.domain.Ticket;

public interface IsCheckedFilter {

    /**
     * Checks if the received in parameter Ticket object is not checked.
     *
     * @param ticket a Ticket object
     * @return boolean false if checked (has a final status)
     */
    boolean isCheckedFilter(Ticket ticket);

}

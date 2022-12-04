package com.tickets.tickets_managemet.service.ticket.filters;

import com.tickets.tickets_managemet.domain.Ticket;

public interface IsCheckedFilter {

    boolean isCheckedFilter(Ticket ticket);

}

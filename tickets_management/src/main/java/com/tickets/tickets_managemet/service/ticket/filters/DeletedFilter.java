package com.tickets.tickets_managemet.service.ticket.filters;

import com.tickets.tickets_managemet.domain.Ticket;

public interface DeletedFilter {

    boolean deletedFilter(Ticket ticket);

}

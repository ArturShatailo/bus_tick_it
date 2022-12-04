package com.tickets.tickets_managemet.service.ticket.filters;

import com.tickets.tickets_managemet.domain.Ticket;

public interface LastCheckFilter {

    boolean lastCheckFilter(Ticket ticket);

}

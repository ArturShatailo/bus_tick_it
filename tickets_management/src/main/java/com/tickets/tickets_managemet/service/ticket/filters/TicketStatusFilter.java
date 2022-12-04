package com.tickets.tickets_managemet.service.ticket.filters;

import com.tickets.tickets_managemet.domain.Ticket;

public interface TicketStatusFilter {

    boolean ticketStatusFilter(Ticket ticket, String status);

}

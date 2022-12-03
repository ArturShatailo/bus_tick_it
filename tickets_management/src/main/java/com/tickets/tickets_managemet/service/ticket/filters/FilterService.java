package com.tickets.tickets_managemet.service.ticket.filters;

public interface FilterService extends
        DeletedFilter,
        IsCheckedFilter,
        LastCheckFilter,
        PaymentStatusFilter,
        TicketStatusFilter {


}

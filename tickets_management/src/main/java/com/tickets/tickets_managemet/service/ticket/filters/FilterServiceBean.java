package com.tickets.tickets_managemet.service.ticket.filters;

import com.tickets.tickets_managemet.domain.Ticket;
import com.tickets.tickets_managemet.service.ticket.TicketInfoServiceBean;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
@AllArgsConstructor
public class FilterServiceBean implements FilterService{

    private final TicketInfoServiceBean ticketInfo;

    @Override
    public boolean deletedFilter(Ticket ticket) {
        return !ticket.getDeleted();
    }

    @Override
    public boolean isCheckedFilter(Ticket ticket) {
        return !ticket.getIs_checked();
    }

    @Override
    public boolean lastCheckFilter(Ticket ticket) {
        return new Date().getTime() - ticket.getLast_check_date().getTime() > 1000;
    }

    @Override
    public boolean paymentStatusFilter(Ticket ticket, String status) {
        return ticketInfo.getTicketInfo(ticket.getId()).payment_status.equals(status);
    }

    @Override
    public boolean ticketStatusFilter(Ticket ticket, String status) {
        return ticket.getCurrent_status().equals(status);
    }

}

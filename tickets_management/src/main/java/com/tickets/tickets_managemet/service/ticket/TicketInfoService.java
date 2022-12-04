package com.tickets.tickets_managemet.service.ticket;

import com.tickets.tickets_managemet.domain.dto.TicketInfo;

public interface TicketInfoService {

    /**
     * Returns TicketInfo object with information about Ticket object received from database
     * according to the received in parameter id.
     *
     * @param id a Ticket id value
     * @return TicketInfo created object
     */
    TicketInfo getTicketInfo(Long id);

}

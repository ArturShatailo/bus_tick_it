package com.tickets.tickets_managemet.service.route;

import com.tickets.tickets_managemet.domain.Ticket;

public interface PurchaseUpdateService {

    /**
     * Adds received Ticket into Bought_tickets list of Route found by received id.
     * Calls sellTicket method of Route object that reduce available tickets by 1.
     * Saves all the changes in database.
     *
     * @param id     the id of Route
     * @param ticket the Ticket object
     */
    void updateSell(Long id, Ticket ticket);

    /**
     * Removes received Ticket from Bought_tickets list of Route found by received id.
     * Calls backTicket method of Route object that increase available tickets by 1.
     * Saves all the changes in database.
     *
     * @param id     the id of Route
     * @param ticket the Ticket object
     */
    void updateCancel(Long id, Ticket ticket);

}

package com.tickets.tickets_managemet.service.route;

import com.tickets.tickets_managemet.domain.Ticket;

public interface PurchaseUpdateService {

    void updateSell(Long id, Ticket ticket);

    void updateCancel(Long id, Ticket ticket);

}

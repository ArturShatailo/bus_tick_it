package com.tickets.tickets_managemet.controller;

import com.tickets.tickets_managemet.domain.ClientDTO;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;

public interface TicketsManagement {

    /**
     * Receives ClientDTO and route ID and calls buy method of TicketPurchaseService for
     * purchase processing. The new Ticket and new Payment will be created and saved in database.
     * The Route available tickets amount will be changed.
     * Returns id of the created Ticket.
     *
     * @param clientDTO the Client DTO
     * @param route_id  the Route id
     * @return the id of created Ticket entity
     */
    @PostMapping("/buy")
    Long buyTicket(@RequestBody @Valid ClientDTO clientDTO, @RequestParam Long route_id);


    @GetMapping("/check")
    Map<Long, Integer> checkTickets();

}

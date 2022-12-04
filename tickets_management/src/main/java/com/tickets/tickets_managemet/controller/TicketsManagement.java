package com.tickets.tickets_managemet.controller;

import com.tickets.tickets_managemet.domain.dto.ClientDTO;
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


    /**
     * Checks status of payment of each Ticket and finds ones with "NEW" status. Filters them according
     * to the FilterService methods. Sets new statuses for
     * payments of filtered tickets randomly. Saves all the changes in database.
     * Tickets with "FAIL" status will cause adding Route data into the created map
     * that will be returned.
     *
     * @return map of id of routes (keys) and amount of available tickets on routes (values).
     */
    @GetMapping("/check")
    Map<Long, Integer> checkTickets();

}

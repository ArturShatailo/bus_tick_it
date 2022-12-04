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
     * to the status, time of the last check, deleted boolean and checked boolean. Sets new statuses for
     * payments of filtered tickets randomly. Saves all the changes in database.
     * In case of any ticket receives status "FAIL", the id of route as a key and amount of available tickets
     * as a value will be added to the created map. Returns created map of routes ids and theirs
     * available tickets amounts.
     *
     * @return map of id of routes (keys) and amount of available tickets on routes (values).
     */
    @GetMapping("/check")
    Map<Long, Integer> checkTickets();

}

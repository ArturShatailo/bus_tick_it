package com.tickets.tickets_managemet.controller;


import com.tickets.tickets_managemet.domain.ClientDTO;

import com.tickets.tickets_managemet.service.ticket.TicketPurchaseServiceBean;
import com.tickets.tickets_managemet.service.tickets_status_processor.TicketProcessorServiceBean;
import com.tickets.tickets_managemet.util.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping(value = "/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TicketManagementController {

    private final TicketPurchaseServiceBean purchaseProcessing;

    private final ClientMapper clientMapper;

    private final TicketProcessorServiceBean ticketProcessorServiceBean;

    @PostMapping("/buy")
    @ResponseStatus(HttpStatus.CREATED)
    public Long buyTicket(@RequestBody /*@Valid*/ ClientDTO clientDTO, @RequestParam Long route_id){
        return purchaseProcessing.buyTicket(clientMapper.toObject(clientDTO), route_id);
    }

    @GetMapping("/check")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<Long, Integer> checkTickets(){
        return ticketProcessorServiceBean.ticketsCheck();
    }

}

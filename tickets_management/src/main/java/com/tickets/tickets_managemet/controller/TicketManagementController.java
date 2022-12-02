package com.tickets.tickets_managemet.controller;

import com.tickets.clients_management.domain.Client;
import com.tickets.tickets_managemet.service.ticket.TicketPurchaseProcessingServiceBean;
import com.tickets.tickets_managemet.service.tickets_status_processor.TicketProcessorServiceBean;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/tickets")
@AllArgsConstructor
public class TicketManagementController {

    private final TicketPurchaseProcessingServiceBean purchaseProcessing;

    private final TicketProcessorServiceBean ticketProcessorServiceBean;

    @PostMapping("/buy")
    @ResponseStatus(HttpStatus.CREATED)
    public Long buyTicket(@RequestBody /*@Valid*/ Client client, @RequestParam Long route_id){
        return purchaseProcessing.ticketPurchaseProcessing(client, route_id);
    }

    @GetMapping("/check")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<Long, Integer> buyTicket(){
        return ticketProcessorServiceBean.ticketsCheck();
    }

}

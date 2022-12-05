package com.tickets.tickets_managemet.controller;

import com.tickets.tickets_managemet.domain.dto.ClientDTO;
import com.tickets.tickets_managemet.service.ticket.TicketPurchaseServiceBean;
import com.tickets.tickets_managemet.service.tickets_status_processor.TicketProcessorServiceBean;
import com.tickets.tickets_managemet.util.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TicketManagementController implements TicketsManagement{

    private final TicketPurchaseServiceBean purchaseProcessing;

    private final ClientMapper clientMapper;

    private final TicketProcessorServiceBean ticketProcessor;

    @Override
    @PostMapping("/buy/{route_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Long buyTicket(@RequestBody @Valid ClientDTO clientDTO, @PathVariable Long route_id){
        log.info("[Ticket system] Start method buyTicket with endpoint /api/tickets/buy/{route_id}");
        return purchaseProcessing.buyTicket(clientMapper.toObject(clientDTO), route_id);
    }

    @Override
    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    public Map<Long, Integer> checkTickets(){
        log.info("[Ticket system] Start method checkTickets with endpoint /api/tickets/check");
        return ticketProcessor.ticketsCheck();
    }

}

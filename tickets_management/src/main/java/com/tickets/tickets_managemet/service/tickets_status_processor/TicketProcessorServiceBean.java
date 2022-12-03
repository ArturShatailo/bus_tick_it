package com.tickets.tickets_managemet.service.tickets_status_processor;

import com.tickets.tickets_managemet.domain.Route;
import com.tickets.tickets_managemet.domain.Ticket;
import com.tickets.tickets_managemet.repository.RouteRepository;
import com.tickets.tickets_managemet.repository.TicketRepository;
import com.tickets.tickets_managemet.service.ticket.TicketInfoServiceBean;
import com.tickets.tickets_managemet.util.configuration.TicketConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Transactional
public class TicketProcessorServiceBean implements TicketProcessorService{

    private final TicketRepository ticketRepository;

    private final TicketInfoServiceBean ticketInfo;

    private final RouteRepository routeRepository;

    private final TicketConfig ticketConfig;

    @Transactional
    @Override
    public Map<Long, Integer> ticketsCheck() {

        Map<Long, Integer> availableRoutes = new HashMap<>();

        List<Ticket> ticketsToCheck = ticketRepository.findAll().stream()
                .filter(ticket -> !ticket.getDeleted())
                .filter(ticket -> !ticket.getIs_checked())
                .filter(ticket -> getPaymentStatus(ticket.getId()).equals("NEW"))
                .filter(this::isNotTimeToCheck)
                .toList();

        for (Ticket ticket : ticketsToCheck) {
            Route route = ticket.getRoute();
            String status = defineTicketStatus(ticket);
            if (status.equals("FAIL"))
                availableRoutes.put(route.getId(), route.getAvailable_tickets_amount());
        }
        return availableRoutes;
    }

    public String defineTicketStatus(Ticket ticket){

        String newPaymentStatus = randomPaymentStatusForTicket(ticket.getPayment_id());

        if (!newPaymentStatus.equals("NEW")) {
            if (newPaymentStatus.equals("FAIL")) {
                Route route = ticket.getRoute();
                route.getBought_tickets().remove(ticket);
                route.backTicket();
                routeRepository.save(route);
            }

            ticket.setCurrent_status(newPaymentStatus);
            ticket.setIs_checked(true);
            ticket.setLast_check_date(new Date());
            ticketRepository.save(ticket);
        }
    return ticket.getCurrent_status();
    }

    private String randomPaymentStatusForTicket(Long paymentID) {
        String uri ="http://localhost:8083/pay/status/{id}";
        return ticketConfig.restTemplate().getForObject(uri, String.class, paymentID);
    }

    private boolean isNotTimeToCheck(Ticket ticket) {
        return new Date().getTime() - ticket.getLast_check_date().getTime() > 1000;
    }

    private String getPaymentStatus(Long id) {
        return ticketInfo.getTicketInfo(id).payment_status;
    }

}

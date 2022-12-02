package com.tickets.tickets_managemet.service.tickets_status_processor;

import com.tickets.tickets_managemet.domain.Route;
import com.tickets.tickets_managemet.domain.Ticket;
import com.tickets.tickets_managemet.repository.TicketRepository;
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

//    private final PaymentStatusServiceBean paymentStatusServiceBean;
//
//    private final TicketRepository ticketRepository;

    @Transactional
    @Override
    public Map<Long, Integer> ticketsCheck() {

        Map<Long, Integer> availableRoutes = new HashMap<>();

//        List<Ticket> ticketsToCheck = ticketRepository.findAll().stream()
//                .filter(ticket -> !ticket.getDeleted())
//                .filter(ticket -> !ticket.getIs_checked())
//                .filter(ticket -> ticket.getPayment().getStatus().equals("NEW"))
//                .filter(this::isNotTimeToCheck)
//                .toList();
//
//        for (Ticket ticket : ticketsToCheck) {
//            Route route = ticket.getRoute();
//            String status = defineTicketStatus(ticket);
//            if (status.equals("FAIL"))
//                availableRoutes.put(route.getId(), route.getAvailable_tickets_amount());
//        }
        return availableRoutes;
    }

    public String defineTicketStatus(Ticket ticket){

//        String newPaymentStatus = paymentStatusServiceBean
//                .status_processing(ticket.getPayment().getId());
//
//        if (!newPaymentStatus.equals("NEW")) {
//            if (newPaymentStatus.equals("FAIL")) {
//                Route route = ticket.getRoute();
//                //route.getBought_tickets().remove(ticket);
//                //route.backTicket();
//                //routeRepository.save(route);
//            }
//
//            ticket.setCurrent_status(newPaymentStatus);
//            ticket.setIs_checked(true);
//            ticket.setLast_check_date(new Date());
//            ticketRepository.save(ticket);
//        }
    return ticket.getCurrent_status();
    }

    private boolean isNotTimeToCheck(Ticket ticket) {
        return new Date().getTime() - ticket.getLast_check_date().getTime() > 1000;
    }

}

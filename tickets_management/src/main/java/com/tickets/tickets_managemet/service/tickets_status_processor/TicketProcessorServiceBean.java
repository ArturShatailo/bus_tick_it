package com.tickets.tickets_managemet.service.tickets_status_processor;

import com.tickets.tickets_managemet.domain.Ticket;
import com.tickets.tickets_managemet.repository.TicketRepository;
import com.tickets.tickets_managemet.service.route.RouteCrudServiceBean;
import com.tickets.tickets_managemet.service.ticket.filters.FilterServiceBean;
import com.tickets.tickets_managemet.util.configuration.TicketConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class TicketProcessorServiceBean implements TicketProcessorService{

    private final TicketRepository ticketRepository;

    private final RouteCrudServiceBean routeCrudServiceBean;

    private final TicketConfig ticketConfig;

    private final FilterServiceBean filter;

    @Transactional
    @Override
    public Map<Long, Integer> ticketsCheck() {

        return ticketRepository.findAll().stream()
                .filter(ticket -> filter.paymentStatusFilter(ticket, "NEW"))
                .filter(filter::deletedFilter)
                .filter(filter::isCheckedFilter)
                .filter(filter::lastCheckFilter)
                .map(this::defineTicketStatus)
                .filter(ticket -> filter.ticketStatusFilter(ticket, "FAIL"))
                .collect(Collectors.toMap(
                        k -> k.getRoute().getId(),
                        v -> v.getRoute().getAvailable_tickets_amount(),
                        (n1, n2) -> n2
                ));

//        Map<Long, Integer> availableRoutes = new HashMap<>();
//
//        List<Ticket> ticketsToCheck = ticketRepository.findAll().stream()
//                .filter(ticket -> !ticket.getDeleted())
//                .filter(ticket -> !ticket.getIs_checked())
//                .filter(ticket -> getPaymentStatus(ticket.getId()).equals("NEW"))
//                .filter(this::checkPeriod)
//                .toList();
//
//        for (Ticket ticket : ticketsToCheck) {
//            Route route = ticket.getRoute();
//            String status = defineTicketStatus(ticket);
//            if (status.equals("FAIL"))
//                availableRoutes.put(route.getId(), route.getAvailable_tickets_amount());
//        }

        //return availableRoutes;
    }

    public Ticket defineTicketStatus(Ticket ticket){

        String newPaymentStatus = randomPaymentStatusForTicket(ticket.getPayment_id());

        if (!newPaymentStatus.equals("NEW")) {
            if (newPaymentStatus.equals("FAIL")) {
                routeProcessingCheck(ticket);
            }
            ticket = ticketProcessingCheck(ticket, newPaymentStatus);
        }
        return ticket;
    }

    private String randomPaymentStatusForTicket(Long paymentID) {
        String uri ="http://localhost:8083/pay/status/{id}";
        return ticketConfig.restTemplate().getForObject(uri, String.class, paymentID);
    }

    private Ticket ticketProcessingCheck(Ticket ticket, String status) {
        ticket.setCurrent_status(status);
        ticket.setIs_checked(true);
        ticket.setLast_check_date(new Date());
        return ticketRepository.save(ticket);
    }

    private void routeProcessingCheck(Ticket ticket) {
        routeCrudServiceBean.updateCancel(ticket.getRoute().getId(), ticket);
    }

}

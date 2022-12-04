package com.tickets.tickets_managemet.service.tickets_status_processor;

import com.tickets.tickets_managemet.domain.Ticket;
import com.tickets.tickets_managemet.repository.TicketRepository;
import com.tickets.tickets_managemet.service.route.RouteCrudServiceBean;
import com.tickets.tickets_managemet.service.ticket.filters.FilterServiceBean;
import com.tickets.tickets_managemet.util.configuration.TicketConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
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

        log.info("[Ticket system] Start method ticketsCheck");

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
    }

    public Ticket defineTicketStatus(Ticket ticket){

        //define new status for payment
        String newPaymentStatus = randomPaymentStatusForTicket(ticket.getPayment_id());

        log.info("[Ticket system] New status {} for payment with id {} is defined for ticket with id {}",
                newPaymentStatus, ticket.getPayment_id(), ticket.getId());

        //simple logic for further actions according to the received random status
        if (!newPaymentStatus.equals("NEW")) {
            if (newPaymentStatus.equals("FAIL")) {
                routeProcessingCheck(ticket);
            }
            ticket = ticketProcessingCheck(ticket, newPaymentStatus);
        }
        return ticket;
    }

    private String randomPaymentStatusForTicket(Long paymentID) {
        String uri = ticketConfig.paymentSystemSetStatus();

        log.info("[Ticket system] HTTP request to uri {} with path variable id {} " +
                "will be sent to set random Payment status", uri, paymentID);

        return ticketConfig.restTemplate().getForObject(uri, String.class, paymentID);
    }

    //save changed Ticket data in database
    private Ticket ticketProcessingCheck(Ticket ticket, String status) {
        log.info("[Ticket system] Ticket with id {} and status {} will be saved with changes", ticket.getId(), status);

        ticket.setCurrent_status(status);
        ticket.setIs_checked(true);
        ticket.setLast_check_date(new Date());
        return ticketRepository.save(ticket);
    }

    //cancel the ticket trade in case of receiving final failed status
    private void routeProcessingCheck(Ticket ticket) {
        routeCrudServiceBean.updateCancel(ticket.getRoute().getId(), ticket);
    }

}

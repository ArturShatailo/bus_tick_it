package com.tickets.tickets_managemet.service.ticket;

import com.tickets.clients_management.domain.Client;
import com.tickets.clients_management.service.ClientCrudServiceBean;
import com.tickets.payment_system.domain.Payment;
import com.tickets.payment_system.service.PayServiceBean;
import com.tickets.payment_system.service.PaymentCrudServiceBean;
import com.tickets.tickets_managemet.domain.Route;
import com.tickets.tickets_managemet.domain.Ticket;
import com.tickets.tickets_managemet.repository.TicketRepository;
import com.tickets.tickets_managemet.service.route.RouteCrudServiceBean;
import com.tickets.tickets_managemet.service.route.RouteTicketsAvailabilityServiceBean;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

@AllArgsConstructor
@Service
public class TicketPurchaseProcessingServiceBean implements TicketPurchaseProcessingService{

    private final RouteCrudServiceBean routeCrudServiceBean;

    private final RouteTicketsAvailabilityServiceBean routeTicketsAvailability;

    private final PayServiceBean payServiceBean;

    private final PaymentCrudServiceBean paymentCrudServiceBean;

    private final TicketRepository ticketRepository;

    private final ClientCrudServiceBean clientCrudServiceBean;

    @Transactional
    @Override
    public Long ticketPurchaseProcessing(Client c, Long route_id) {

        routeTicketsAvailability.check(route_id);

        Client client = clientCrudServiceBean.getById(c.getId());

        //save Ticket object block
        Ticket ticket =
                ticketRepository.save(Ticket.builder()
                        .route(getRoute(route_id))
                        .payment(getPayment(payServiceBean.do_payment(client)))
                        .current_status("NEW")
                        .last_check_date(new Date())
                        .client(client)
                        .deleted(false)
                        .is_checked(false)
                        .build());

        //change Route object
        updateRouteAfterTicketSell(route_id, ticket);

        return ticket.getId();
    }

    private Route getRoute(Long id){
        return routeCrudServiceBean.getById(id);
    }

    private Payment getPayment(Long id){
        return paymentCrudServiceBean.getById(id);
    }

    private void updateRouteAfterTicketSell(Long route_id, Ticket ticket){
        routeCrudServiceBean.update(route_id, ticket);
    }

}

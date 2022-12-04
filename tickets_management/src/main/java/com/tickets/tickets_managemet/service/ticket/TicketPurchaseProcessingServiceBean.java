package com.tickets.tickets_managemet.service.ticket;

import com.tickets.tickets_managemet.domain.Client;
import com.tickets.tickets_managemet.domain.Route;
import com.tickets.tickets_managemet.domain.Ticket;
import com.tickets.tickets_managemet.repository.ClientRepository;
import com.tickets.tickets_managemet.repository.TicketRepository;
import com.tickets.tickets_managemet.service.route.RouteCrudServiceBean;
import com.tickets.tickets_managemet.service.route.RouteTicketsAvailabilityServiceBean;
import com.tickets.tickets_managemet.util.configuration.TicketConfig;
import com.tickets.tickets_managemet.util.exceptions.client.ClientNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

@AllArgsConstructor
@Service
public class TicketPurchaseProcessingServiceBean implements TicketPurchaseProcessingService{

    private final RouteCrudServiceBean routeCrudServiceBean;

    private final RouteTicketsAvailabilityServiceBean routeTicketsAvailability;

    private final TicketRepository ticketRepository;

    private final ClientRepository clientRepository;

    private final TicketConfig ticketConfig;

    @Transactional
    @Override
    public Long ticketPurchaseProcessing(Client client, Long route_id) {

        routeTicketsAvailability.check(route_id);

        Route route = getRoute(route_id);

        //save Ticket object block
        Ticket ticket =
                ticketRepository.save(Ticket.builder()
                        .route(route)
                        .payment_id(getPaymentID(client, route.getCost()))
                        .current_status("NEW")
                        .last_check_date(new Date())
                        .client(getClient(client.getEmail()))
                        .deleted(false)
                        .is_checked(false)
                        .build());

        //change Route object
        updateRouteAfterTicketSell(route_id, ticket);

        return ticket.getId();
    }

    private Client getClient(String email) {
        return clientRepository.findClientByEmail(email)
                .orElseThrow(() -> new ClientNotFoundException("Client with email: " + email + " was not found in database"));
    }

    private Route getRoute(Long id){
        return routeCrudServiceBean.getById(id);
    }

    private void updateRouteAfterTicketSell(Long route_id, Ticket ticket){
        routeCrudServiceBean.updateSell(route_id, ticket);
    }

    public Long getPaymentID(Client client, Double amount) {
        String uri = ticketConfig.paymentSystemPay();
        return ticketConfig.restTemplate().postForObject(uri, new HttpEntity<>(client), Long.class, amount);
    }

}

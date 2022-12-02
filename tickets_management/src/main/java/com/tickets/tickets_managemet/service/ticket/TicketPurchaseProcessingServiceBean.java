package com.tickets.tickets_managemet.service.ticket;

import com.tickets.tickets_managemet.domain.Client;
import com.tickets.tickets_managemet.domain.Route;
import com.tickets.tickets_managemet.domain.Ticket;
import com.tickets.tickets_managemet.repository.ClientRepository;
import com.tickets.tickets_managemet.repository.TicketRepository;
import com.tickets.tickets_managemet.service.route.RouteCrudServiceBean;
import com.tickets.tickets_managemet.service.route.RouteTicketsAvailabilityServiceBean;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.Date;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class TicketPurchaseProcessingServiceBean implements TicketPurchaseProcessingService{

    private final RouteCrudServiceBean routeCrudServiceBean;

    private final RouteTicketsAvailabilityServiceBean routeTicketsAvailability;

    private final TicketRepository ticketRepository;

    private final ClientRepository clientRepository;

    private final RestTemplate paymentRestTemplate;

    @Transactional
    @Override
    public Long ticketPurchaseProcessing(Client client, Long route_id) {

        routeTicketsAvailability.check(route_id);

        //save Ticket object block
        Ticket ticket =
                ticketRepository.save(Ticket.builder()
                        .route(getRoute(route_id))
                        .payment_id(getPaymentID(client))
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
                .orElseThrow(() -> new NoSuchElementException("Can't find Client with email: "+email));
    }

    public Long getPaymentID(Client client) {
        String uri ="http://localhost:8083/pay/{amount}";
        return paymentRestTemplate.postForObject(uri, new HttpEntity<>(client), Long.class, 1.0);
    }

    private Route getRoute(Long id){
        return routeCrudServiceBean.getById(id);
    }

    private void updateRouteAfterTicketSell(Long route_id, Ticket ticket){
        routeCrudServiceBean.updateSell(route_id, ticket);
    }

}

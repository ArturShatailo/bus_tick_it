package com.tickets.tickets_managemet.service.route;

import com.tickets.tickets_managemet.domain.Route;
import com.tickets.tickets_managemet.domain.Ticket;
import com.tickets.tickets_managemet.repository.RouteRepository;
import com.tickets.tickets_managemet.util.exceptions.route.RouteNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RouteCrudServiceBean implements CrudService<Route>, PurchaseUpdateService{

    private final RouteRepository routeRepository;

    @Override
    public Route getById(Long id) {
        log.info("[Ticket system] Start method getById with id {}", id);
        return routeRepository.findById(id)
                .orElseThrow(() -> new RouteNotFoundException("Route with id: " + id + " was not found in database"));
    }

    @Override
    public void updateSell(Long id, Ticket ticket) {
        log.info("[Ticket system] Start method updateSell with id {} and ticket with id {}", id, ticket.getId());

        Route route = getById(id);
        route.getBought_tickets().add(ticket);
        route.sellTicket();
        routeRepository.save(route);

        log.info("[Ticket system] ticket with id {} added into Bought_tickets list of route with id {}. " +
                "Available tickets value reduced to {}", ticket.getId(), id, route.getAvailable_tickets_amount());
    }

    @Override
    public void updateCancel(Long id, Ticket ticket) {
        log.info("[Ticket system] Start method updateCancel with id {} and ticket with id {}", id, ticket.getId());

        Route route = getById(id);
        route.getBought_tickets().remove(ticket);
        route.backTicket();
        routeRepository.save(route);

        log.info("[Ticket system] ticket with id {} added into Bought_tickets list of route id {}. " +
                "Available tickets value increased to {}", ticket.getId(), id, route.getAvailable_tickets_amount());
    }

    @Override
    public List<Route> getAllRoutes() {
        log.info("[Ticket system] All the Route objects will be uploaded from database");
        return routeRepository.findAll();
    }
}

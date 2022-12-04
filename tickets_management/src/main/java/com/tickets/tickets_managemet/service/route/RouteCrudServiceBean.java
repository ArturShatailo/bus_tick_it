package com.tickets.tickets_managemet.service.route;

import com.tickets.tickets_managemet.domain.Route;
import com.tickets.tickets_managemet.domain.Ticket;
import com.tickets.tickets_managemet.repository.RouteRepository;
import com.tickets.tickets_managemet.util.exceptions.route.RouteNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RouteCrudServiceBean implements CrudService<Route>, PurchaseUpdateService{

    private final RouteRepository routeRepository;

    @Override
    public Route getById(Long id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new RouteNotFoundException("Route with id: " + id + " was not found in database"));
    }

    @Override
    public void updateSell(Long id, Ticket ticket) {
        Route route = getById(id);
        route.getBought_tickets().add(ticket);
        route.sellTicket();
        routeRepository.save(route);
    }

    @Override
    public void updateCancel(Long id, Ticket ticket) {
        Route route = getById(id);
        route.getBought_tickets().remove(ticket);
        route.backTicket();
        routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }
}

package com.tickets.tickets_managemet.service.route;

import com.tickets.tickets_managemet.domain.Route;
import com.tickets.tickets_managemet.domain.Ticket;
import com.tickets.tickets_managemet.repository.RouteRepository;
import com.tickets.tickets_managemet.service.CrudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class RouteCrudServiceBean implements CrudService<Route> {

    private final RouteRepository routeRepository;

    @Override
    public Route getById(Long id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find Route with id: " + id));
    }

    @Override
    public void update(Long id, Route route) {
        routeRepository.findById(id).map(
                r -> {
                    r.setAvailable_tickets_amount(route.getAvailable_tickets_amount());
                    r.setBought_tickets(route.getBought_tickets());
                    r.setDeparture_time(route.getDeparture_time());
                    r.setDeleted(route.getDeleted());
                    r.setFrom(route.getFrom());
                    r.setCost(route.getCost());
                    r.setTo(route.getTo());
                    return routeRepository.save(r);
                }
        ).orElseThrow(() -> new NoSuchElementException("Can't find an Route with id = " + id));
    }

    public void updateSell(Long id, Ticket ticket) {
        routeRepository.findById(id).map(
                p -> {
                    p.getBought_tickets().add(ticket);
                    p.sellTicket();
                    return routeRepository.save(p);
                }
                ).orElseThrow(() -> new NoSuchElementException("Can't find an Route with id = " + id));
    }

    public void updateCancel(Long id, Ticket ticket) {
        routeRepository.findById(id).map(
                p -> {
                    p.getBought_tickets().remove(ticket);
                    p.backTicket();
                    return routeRepository.save(p);
                }
        ).orElseThrow(() -> new NoSuchElementException("Can't find an Route with id = " + id));
    }

    @Override
    public Route create(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public void delete(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find Route with id: " + id));
        route.setDeleted(true);
        routeRepository.save(route);
    }
}

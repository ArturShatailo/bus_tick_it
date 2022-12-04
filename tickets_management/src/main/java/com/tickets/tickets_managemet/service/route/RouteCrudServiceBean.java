package com.tickets.tickets_managemet.service.route;

import com.tickets.tickets_managemet.domain.Route;
import com.tickets.tickets_managemet.domain.Ticket;
import com.tickets.tickets_managemet.repository.RouteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class RouteCrudServiceBean implements CrudService<Route>, PurchaseUpdateService{

    private final RouteRepository routeRepository;

    @Override
    public Route getById(Long id) {
        return routeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find Route with id: " + id));
    }

    @Override
    public void updateSell(Long id, Ticket ticket) {
        routeRepository.findById(id).map(
                p -> {
                    p.getBought_tickets().add(ticket);
                    p.sellTicket();
                    return routeRepository.save(p);
                }
                ).orElseThrow(() -> new NoSuchElementException("Can't find an Route with id = " + id));
    }

    @Override
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
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }
}

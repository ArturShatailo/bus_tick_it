package com.tickets.tickets_managemet.service.route;

import com.tickets.tickets_managemet.domain.Route;
import com.tickets.tickets_managemet.repository.RouteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class RouteValidationServiceBean implements RouteValidationService {

    private final RouteRepository routeRepository;

    @Override
    public void checkTicketsAmount(Route route) {
        if (route.getAvailable_tickets_amount() <= 0)
            throw new NoSuchElementException("All the tickets are sold for the Route with id: " + route.getId());
    }

    @Override
    public void checkDepartureTime(Route route) {
        if (route.getDeparture_time().getTime() - new Date().getTime() < 300000)
            throw new NoSuchElementException("Departure time is too close, tickets trading is closed");
    }

    @Override
    public void validate(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cant find Route with id: " + id));
        checkTicketsAmount(route);
        checkDepartureTime(route);
    }
}

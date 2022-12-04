package com.tickets.tickets_managemet.service.route;

import com.tickets.tickets_managemet.domain.Route;
import com.tickets.tickets_managemet.repository.RouteRepository;
import com.tickets.tickets_managemet.util.exceptions.route.RouteNotFoundException;
import com.tickets.tickets_managemet.util.exceptions.route.RouteUnavailableException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@AllArgsConstructor
public class RouteValidationServiceBean implements RouteValidationService {

    private final RouteRepository routeRepository;

    @Override
    public void checkTicketsAmount(Route route) {
        if (route.getAvailable_tickets_amount() <= 0)
            throw new RouteUnavailableException("All the tickets are sold for the Route with id: " + route.getId());
    }

    @Override
    public void checkDepartureTime(Route route) {
        if (route.getDeparture_time().getTime() - new Date().getTime() < 300000)
            throw new RouteUnavailableException("Departure time is too close, tickets trading is closed for the Route with id: " + route.getId());
    }

    @Override
    public void validate(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RouteNotFoundException("Route with id: " + id + " was not found in database"));
        checkTicketsAmount(route);
        checkDepartureTime(route);
    }
}

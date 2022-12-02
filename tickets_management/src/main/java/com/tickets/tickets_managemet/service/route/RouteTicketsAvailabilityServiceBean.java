package com.tickets.tickets_managemet.service.route;

import com.tickets.tickets_managemet.repository.RouteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class RouteTicketsAvailabilityServiceBean implements RouteTicketsAvailabilityService{

    private final RouteRepository routeRepository;

    @Override
    public void check(Long id) {
        int availableAmount = routeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cant find Route with id: " + id))
                .getAvailable_tickets_amount();
        if (availableAmount <= 0) throw new NoSuchElementException("All the tickets are sold for the Route with id: " + id);
    }
}

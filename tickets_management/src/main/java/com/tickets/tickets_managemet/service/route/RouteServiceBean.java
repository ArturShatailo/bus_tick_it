package com.tickets.tickets_managemet.service.route;

import com.tickets.tickets_managemet.domain.Route;
import com.tickets.tickets_managemet.repository.RouteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RouteServiceBean implements RoutesListService{

    private final RouteRepository routeRepository;

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

}

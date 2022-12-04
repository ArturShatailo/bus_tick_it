package com.tickets.tickets_managemet.controller;

import com.tickets.tickets_managemet.domain.Route;
import com.tickets.tickets_managemet.service.route.RouteCrudServiceBean;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/routes", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RouteController implements RoutesManagement{

    private final RouteCrudServiceBean routeCrudServiceBean;

    @Override
    @GetMapping("/")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Route> buyTicket(){
        return routeCrudServiceBean.getAllRoutes();
    }

}

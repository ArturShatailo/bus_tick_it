package com.tickets.tickets_managemet.controller;

import com.tickets.tickets_managemet.domain.dto.RouteInfo;
import com.tickets.tickets_managemet.service.route.RouteCrudServiceBean;
import com.tickets.tickets_managemet.util.mapper.RouteMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/routes", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RouteController implements RoutesManagement{

    private final RouteCrudServiceBean routeCrudServiceBean;

    private final RouteMapper routeMapper;

    @Override
    @GetMapping("/")
    @ResponseStatus(HttpStatus.FOUND)
    public List<RouteInfo> getAllRoutes(){
        log.info("[Ticket system] Start method getAllRoutes");
        return routeMapper.toRouteInfoList(routeCrudServiceBean.getAllRoutes());
    }

}

package com.tickets.tickets_managemet.util.mapper;

import com.tickets.tickets_managemet.domain.Route;
import com.tickets.tickets_managemet.domain.dto.RouteInfo;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RouteMapperImpl implements RouteMapper{

    @Override
    public RouteInfo toRouteInfo(Route route) {
        if (route == null) return null;

        RouteInfo routeInfo = new RouteInfo();
        routeInfo.id = route.getId();
        routeInfo.cost = route.getCost();
        routeInfo.from = route.getFrom().getName();
        routeInfo.to = route.getTo().getName();
        routeInfo.available_tickets_amount = route.getAvailable_tickets_amount();
        routeInfo.departure_time = route.getDeparture_time();

        return routeInfo;
    }

    @Override
    public List<RouteInfo> toRouteInfoList(List<Route> routes) {
        return routes.stream().map(this::toRouteInfo).toList();
    }
}

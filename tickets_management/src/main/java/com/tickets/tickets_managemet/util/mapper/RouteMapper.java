package com.tickets.tickets_managemet.util.mapper;

import com.tickets.tickets_managemet.domain.Route;
import com.tickets.tickets_managemet.domain.dto.RouteInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring", uses = RouteMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RouteMapper {

    RouteInfo toRouteInfo(Route route);

    List<RouteInfo> toRouteInfoList(List<Route> routes);


}

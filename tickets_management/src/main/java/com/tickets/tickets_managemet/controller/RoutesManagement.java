package com.tickets.tickets_managemet.controller;

import com.tickets.tickets_managemet.domain.dto.RouteInfo;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

public interface RoutesManagement {


    /**
     * Returns all Route entities from database mapped to RouteInfo DTO
     *
     * @return list of all RouteInfo objects.
     */
    @GetMapping("/")
    List<RouteInfo> getAllRoutes();

}

package com.tickets.tickets_managemet.controller;

import com.tickets.tickets_managemet.domain.Route;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

public interface RoutesManagement {

    @GetMapping("/")
    List<Route> buyTicket();

}

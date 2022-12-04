package com.tickets.tickets_managemet.service.route;

import com.tickets.tickets_managemet.domain.Route;

public interface RouteValidationService {

    void checkTicketsAmount(Route route);

    void checkDepartureTime(Route route);

    void validate(Long id);

}

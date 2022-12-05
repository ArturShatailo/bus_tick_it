package com.tickets.tickets_managemet.service.route;

import com.tickets.tickets_managemet.domain.Route;

public interface RouteValidationService {

    /**
     * Checks tickets amount of the Route received in parameter.
     *
     * @param route Route object
     */
    void checkTicketsAmount(Route route);

    /**
     * Checks departure time of the Route received in parameter.
     *
     * @param route Route object
     */
    void checkDepartureTime(Route route);

    /**
     * Validate Route found by id received in parameter according to the methods
     * checkTicketsAmount(), checkDepartureTime()
     *
     * @param id Route id
     */
    void validate(Long id);

}

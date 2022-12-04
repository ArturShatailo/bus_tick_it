package com.tickets.tickets_managemet.service.route;

public interface RouteTicketsAvailabilityService {

    /**
     * Checks the Route object found by id received in parameter.
     * Validates it according to the RouteValidationService methods.
     *
     * @param id the id
     */
    void check(Long id);

}

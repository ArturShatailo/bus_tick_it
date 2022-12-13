package com.tickets.tickets_managemet.service.route;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RouteTicketsAvailabilityServiceBean implements RouteTicketsAvailabilityService{

    //private final RouteValidationServiceBean validationService;

    private final RouteValidationService validationService;

    @Override
    public void check(Long id) {
        validationService.validate(id);
    }
}

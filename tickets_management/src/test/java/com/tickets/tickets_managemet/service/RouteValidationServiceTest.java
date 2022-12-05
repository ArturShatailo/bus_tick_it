package com.tickets.tickets_managemet.service;

import com.tickets.tickets_managemet.domain.*;
import com.tickets.tickets_managemet.service.route.*;
import com.tickets.tickets_managemet.util.exceptions.route.RouteUnavailableException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.ArrayList;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class RouteValidationServiceTest {

    @InjectMocks
    private RouteValidationServiceBean routeValidationServiceBean;

    private static Route route;

    @BeforeAll
    public static void setup(){

        route = new Route(5L, new Station(), new Station(),
                new Date(), 100.0, 0,
                false, new ArrayList<>());
    }

    @Test
    public void whenCheckTicketsAmountShouldReturnRouteUnavailableException() {
        Exception exception = assertThrows(RouteUnavailableException.class,
                () -> routeValidationServiceBean.checkTicketsAmount(route));
        assertTrue(exception.getMessage().contains("All the tickets are sold for the Route with id: 5"));
    }

    @Test
    public void whenCheckDepartureTimeShouldReturnRouteUnavailableException() {
        Exception exception = assertThrows(RouteUnavailableException.class,
                () -> routeValidationServiceBean.checkDepartureTime(route));
        assertTrue(exception.getMessage().contains("Departure time is too close, tickets trading is closed for the Route with id: 5"));
    }

}

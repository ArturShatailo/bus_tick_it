package com.tickets.tickets_managemet.service;

import com.tickets.tickets_managemet.domain.*;
import com.tickets.tickets_managemet.repository.RouteRepository;
import com.tickets.tickets_managemet.service.route.CrudService;
import com.tickets.tickets_managemet.service.route.PurchaseUpdateService;
import com.tickets.tickets_managemet.service.route.RouteCrudServiceBean;
import com.tickets.tickets_managemet.util.exceptions.route.RouteNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class RouteCrudServiceTest {

    @Mock
    private CrudService<Route> service;

    @Mock
    private PurchaseUpdateService serviceUpdate;

    @Mock
    private RouteRepository routeRepository;

    @InjectMocks
    private RouteCrudServiceBean routeCrudServiceBean;

    private static Ticket ticket;

    private static Route route;

    private static Station st1;

    private static Station st2;

    @BeforeAll
    public static void setup(){

        Client client = new Client(1L, "Fred", "Johnson", "John",
                "fred@fred.com", false, new ArrayList<>());

        st1 = new Station(1L, "Station", new StationAddress());
        st2 = new Station(2L, "Station2", new StationAddress());

        route = new Route(1L, st1, st2,
                new Date(4670194458070L), 100.0, 2,
                false, new ArrayList<>());

        ticket = new Ticket(1L, route, client, 1L,
                new Date(), "NEW", false, false);


    }

    @Test
    public void whenGetAllRoutesShouldReturnList() {

        Route route1 = new Route(2L, st2, st1, new Date(4670194458070L),
                200.0, 3, false, new ArrayList<>());
        List<Route> routes = List.of(route, route1);

        when(routeRepository.findAll()).thenReturn(routes);

        doReturn(routes).when(service).getAllRoutes();

        var servicesTest = service.getAllRoutes();

        Assertions.assertThat(servicesTest).hasSameSizeAs(routes);
        Assertions.assertThat(servicesTest).isInstanceOf(routes.getClass());
        Assertions.assertThat(servicesTest).containsAll(routes);

        verify(service).getAllRoutes();
    }

    @Test
    public void whenUpdateSellShouldChangeRouteState() {

        Route routeTest = new Route(1L, st1, st2, new Date(4670194458070L), 100.0,
                2, false, new ArrayList<>());

        when(routeRepository.findById(routeTest.getId()))
                .thenReturn(Optional.of(routeTest));

        routeTest.getBought_tickets().add(ticket);
        routeTest.sellTicket();

        routeRepository.save(route);
        serviceUpdate.updateSell(route.getId(), ticket);
        Route route1 = routeRepository.findById(route.getId()).orElseThrow();

        Assertions.assertThat(route1.getBought_tickets()).isEqualTo(routeTest.getBought_tickets());
        Assertions.assertThat(route1.getAvailable_tickets_amount())
                .isEqualTo(routeTest.getAvailable_tickets_amount());

        verify(serviceUpdate).updateSell(route.getId(), ticket);
    }

    @Test
    public void whenUpdateCancelShouldChangeRouteState() {

        Route routeTest = new Route(1L, st1, st2, new Date(4670194458070L), 100.0,
                2, false, new ArrayList<>());
        routeTest.getBought_tickets().add(ticket);

        when(routeRepository.findById(routeTest.getId()))
                .thenReturn(Optional.of(routeTest));

        routeTest.getBought_tickets().remove(ticket);
        routeTest.backTicket();

        routeRepository.save(route);
        serviceUpdate.updateCancel(route.getId(), ticket);
        Route route1 = routeRepository.findById(route.getId()).orElseThrow();

        Assertions.assertThat(route1.getBought_tickets()).isEqualTo(routeTest.getBought_tickets());
        Assertions.assertThat(route1.getAvailable_tickets_amount())
                .isEqualTo(routeTest.getAvailable_tickets_amount());

        verify(serviceUpdate).updateCancel(route.getId(), ticket);
    }

    @Test
    public void whenGetByIdShouldReturnRoute() {

        when(routeRepository
                .findById(anyLong()))
                .thenReturn(Optional.ofNullable(route));

        Route routeTest = routeCrudServiceBean.getById(1L);

        assertThat(routeTest.getId()).isSameAs(route.getId());
        assertThat(routeTest.getDeparture_time()).isSameAs(route.getDeparture_time());

        verify(routeRepository).findById(route.getId());
    }

    @Test
    public void whenUnregisteredIdShouldReturnPaymentNotFoundException(){
        Exception exception = assertThrows(RouteNotFoundException.class,
                () -> routeCrudServiceBean.getById(3L));
        assertTrue(exception.getMessage().contains("Route with id: 3 was not found in database"));
    }

}

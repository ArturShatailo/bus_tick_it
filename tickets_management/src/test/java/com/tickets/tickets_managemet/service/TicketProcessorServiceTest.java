package com.tickets.tickets_managemet.service;

import com.tickets.tickets_managemet.domain.Route;
import com.tickets.tickets_managemet.domain.Station;
import com.tickets.tickets_managemet.service.tickets_status_processor.TicketProcessorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class TicketProcessorServiceTest {

    @Mock
    private TicketProcessorService service;

    private static Route route;

    @BeforeAll
    public static void setup(){

        route = new Route(1L, new Station(), new Station(),
                new Date(4670194458070L), 100.0, 2,
                false, new ArrayList<>());
    }


    @Test
    public void whenBuyTicketShouldReturnTicketId() {

        Map<Long, Integer> routes = new HashMap<>();
        routes.put(route.getId(), route.getAvailable_tickets_amount());

        when(service
                .ticketsCheck())
                .thenReturn(routes);

        Map<Long, Integer> routesTest = service.ticketsCheck();

        assertThat(routesTest).hasSizeBetween(0, 1);

        verify(service).ticketsCheck();
    }

}

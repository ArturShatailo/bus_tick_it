package com.tickets.tickets_managemet.controller_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tickets.tickets_managemet.domain.*;
import com.tickets.tickets_managemet.domain.dto.ClientDTO;
import com.tickets.tickets_managemet.domain.dto.RouteInfo;
import com.tickets.tickets_managemet.repository.ClientRepository;
import com.tickets.tickets_managemet.repository.RouteRepository;
import com.tickets.tickets_managemet.repository.TicketRepository;
import com.tickets.tickets_managemet.util.mapper.RouteMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketsManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RouteMapper routeMapper;

    @Resource
    private ClientRepository clientRepository;

    @Resource
    private TicketRepository ticketRepository;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private RouteRepository routeRepository;

    private static Client client;

    private static Route route;

    private static Station st1;

    private static Station st2;

    @BeforeAll
    public static void setup(){

        ClientDTO clientDTO = new ClientDTO("Fred", "Johnson",
                "John", "fred@fred.com");

        client = new Client(1L, "Fred", "Johnson", "John",
                "fred@fred.com", false, new ArrayList<>());

        st1 = new Station(1L, "Station", new StationAddress());
        st2 = new Station(2L, "Station2", new StationAddress());

        route = new Route(1L, st1, st2,
                new Date(4670194458070L), 100.0, 2,
                false, new ArrayList<>());

        Ticket ticket = new Ticket(1L, route, client, 1L,
                new Date(), "NEW", false, false);

    }

    @Test
    public void getAllRoutesController() throws Exception {

        Route route1 = new Route(2L, st2, st1,
                new Date(), 200.0, 3, false, new ArrayList<>());

        routeRepository.save(route);
        routeRepository.save(route1);

        RouteInfo routeI1 = routeMapper.toRouteInfo(route1);
        RouteInfo routeI2 = routeMapper.toRouteInfo(route);

        List<RouteInfo> routes = List.of(routeI1, routeI2);

        mockMvc.perform(get("/api/routes/")
                )
                .andExpect(content().json(objectMapper.writeValueAsString(routes)))
                .andExpect(status().isFound());
    }

    @Test
    public void buyTicketController() throws Exception {

//        clientRepository.save(client);
//        routeRepository.save(route);
//
//        mockMvc.perform(post("/api/tickets/buy/{route_id}", route.getId())
//                        .content(objectMapper.writeValueAsString(clientDTO))
//                        .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(content().string(objectMapper.writeValueAsString(ticket.getId())))
//                .andExpect(status().isCreated());

    }

    @Test
    public void checkTicketsController() throws Exception {

    }

}
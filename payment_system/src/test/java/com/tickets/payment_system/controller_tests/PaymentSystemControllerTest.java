package com.tickets.payment_system.controller_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tickets.payment_system.domain.Client;
import com.tickets.payment_system.domain.Payment;
import com.tickets.payment_system.domain.dto.ClientDTO;
import com.tickets.payment_system.repository.ClientRepository;
import com.tickets.payment_system.repository.PaymentRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentSystemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Resource
    private ClientRepository clientRepository;

    @Resource
    private PaymentRepository paymentRepository;

    private static Client client;

    private static Payment payment;

    private static ClientDTO clientDTO;

    private static List<String> statuses;

    @BeforeAll
    public static void setup(){

        statuses = List.of("NEW", "FAIL", "DONE");

        clientDTO = new ClientDTO("Fred", "Johnson",
                "John", "fred@fred.com");

        client = new Client(1L, "Fred", "Johnson", "John",
                "fred@fred.com", false, new ArrayList<>());

        payment = new Payment(1L, "NEW", 100.0, client, false);

    }

    @Order(1)
    @Test
    public void payController() throws Exception {

        Double amount = 100.0;

        clientRepository.save(client);

        mockMvc.perform(post("/api/pay/{amount}", amount)
                        .content(objectMapper.writeValueAsString(clientDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(content().string(objectMapper.writeValueAsString(payment.getId())))
                .andExpect(status().isCreated());
    }

    @Test
    public void setStatusController() throws Exception {

        paymentRepository.save(payment);

        mockMvc.perform(get("/api/pay/status/{id}", payment.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        Payment paymentNew = paymentRepository.findById(payment.getId())
                .orElseThrow();

        assertTrue(statuses.contains(paymentNew.getStatus()));
    }

    @Test
    public void getPaymentStatusController() throws Exception {

        paymentRepository.save(payment);

        mockMvc.perform(get("/api/pay/st/{id}", payment.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(content().string(payment.getStatus()))
                .andExpect(status().isFound());
    }

    @Test
    public void setStatusesController() throws Exception {

        paymentRepository.save(payment);
        Payment payment1 = new Payment(2L, "FAIL", 200.0, client, false);
        paymentRepository.save(payment1);

        Map<Long, String> result = new HashMap<>();
        result.put(payment.getId(), payment.getStatus());
        result.put(payment1.getId(), payment1.getStatus());

        mockMvc.perform(put("/api/pay/statuses"))
                .andExpect(status().isOk());

        assertTrue(result.containsKey(payment.getId()));
        assertTrue(result.containsKey(payment.getId()));
        assertTrue(statuses.contains(result.get(payment.getId())));
        assertTrue(statuses.contains(result.get(payment1.getId())));
    }
}
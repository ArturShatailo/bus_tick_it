package com.tickets.payment_system.service_test;

import com.tickets.payment_system.domain.Client;
import com.tickets.payment_system.domain.Payment;
import com.tickets.payment_system.domain.dto.ClientDTO;
import com.tickets.payment_system.repository.ClientRepository;
import com.tickets.payment_system.service.PayService;
import com.tickets.payment_system.service.PayServiceBean;
import com.tickets.payment_system.util.exceptions.ClientNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.ArrayList;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class PaymentPayServiceTest {

    @Mock
    private PayService service;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private PayServiceBean payServiceBean;

    private static Client client;

    private static Payment payment;

    private static ClientDTO clientDTO;

    @BeforeAll
    public static void setup(){

        clientDTO = new ClientDTO("Fred", "Johnson",
                "John", "fred@fred.com");

        client = new Client(1L, "Fred", "Johnson", "John",
                "fred@fred.com", false, new ArrayList<>());

        payment = new Payment(1L, "NEW", 100.0, client, false);

    }

    @Test
    public void whenDoPaymentShouldReturnPaymentId() {

        when(clientRepository
                .findClientByEmail(clientDTO.getEmail()))
                .thenReturn(Optional.ofNullable(client));

        doReturn(payment.getId()).when(service).do_payment(client, 100.0);

        var payment_id = service.do_payment(client, 100.0);

        assertThat(payment_id).isEqualTo(payment.getId());

        verify(service).do_payment(client, 100.0);
    }

    @Test
    public void whenUnregisteredClientInPayShouldReturnClientNotFoundException(){

        Client testClient = new Client(4L, "Freddy", "Johnson", "John",
                "freddy@freddy.com", false, new ArrayList<>());

        Exception exception = assertThrows(ClientNotFoundException.class,
                () -> payServiceBean.do_payment(testClient, 200.0));
        assertTrue(exception.getMessage().contains("Can't find client with email freddy@freddy.com in database"));
    }

}

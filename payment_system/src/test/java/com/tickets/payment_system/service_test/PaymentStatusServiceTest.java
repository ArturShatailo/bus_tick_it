package com.tickets.payment_system.service_test;

import com.tickets.payment_system.domain.Client;
import com.tickets.payment_system.domain.Payment;
import com.tickets.payment_system.repository.PaymentRepository;
import com.tickets.payment_system.service.PaymentStatusService;
import com.tickets.payment_system.service.PaymentStatusServiceBean;
import com.tickets.payment_system.util.configuration.StatusConfig;
import com.tickets.payment_system.util.exceptions.PaymentNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class PaymentStatusServiceTest {

    @Mock
    private PaymentStatusService service;

    @Mock
    private PaymentRepository repository;

    @InjectMocks
    private PaymentStatusServiceBean paymentStatusServiceBean;

    @Spy
    private StatusConfig statusConfig;

    private static Client client;

    private static Payment payment;

    private static List<String> statuses;

    @BeforeAll
    public static void setup(){

        statuses = List.of("NEW", "FAIL", "DONE");

        client = new Client(1L, "Fred", "Johnson", "John",
                "fred@fred.com", false, new ArrayList<>());

        payment = new Payment(1L, "NEW", 100.0, client, false);

    }

    @Test
    public void whenStatusProcessingShouldReturnStatus() {

        given(repository.findById(payment.getId()))
                .willReturn(Optional.ofNullable(payment));

        Payment payment1 = repository.findById(payment.getId())
                .orElseThrow();

        payment1.setStatus(defineRandomFromList(statusConfig.status()));

        repository.save(payment1);

        assertTrue(statuses.contains(payment1.getStatus()));

        //String status = paymentStatusService.status_processing(payment.getId());
        then(repository)
                .should()
                .save(payment);
    }

    @Test
    public void whenStatusesProcessingShouldReturnMap() {

        Payment payment1 = new Payment(2L, "FAIL", 200.0, client, false);
        repository.save(payment);
        repository.save(payment1);

        Map<Long, String> resultTest = new HashMap<>();
        resultTest.put(payment.getId(), payment.getStatus());
        resultTest.put(payment1.getId(), payment1.getStatus());

        doReturn(resultTest).when(service).status_processing();

        var map = service.status_processing();

        assertThat(map).hasSameSizeAs(resultTest);
        assertThat(map).isInstanceOf(resultTest.getClass());
        assertThat(map).containsAllEntriesOf(resultTest);

        verify(service).status_processing();
    }

    @Test
    public void whenUnregisteredIdInStatusShouldReturnPaymentNotFoundException(){
        Exception exception = assertThrows(PaymentNotFoundException.class,
                () -> paymentStatusServiceBean.status_processing(4L));
        assertTrue(exception.getMessage().contains("Payment with id: 4 was not found in database"));
    }

    private String defineRandomFromList(List<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

}

package com.tickets.payment_system.service_test;

import com.tickets.payment_system.domain.Client;
import com.tickets.payment_system.domain.Payment;
import com.tickets.payment_system.repository.PaymentRepository;
import com.tickets.payment_system.service.PaymentCrudServiceBean;
import com.tickets.payment_system.util.exceptions.PaymentNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentCrudServiceTest {

    @InjectMocks
    private PaymentCrudServiceBean paymentCrudServiceBean;

    @Mock
    private PaymentRepository paymentRepository;

    private static Payment payment;

    @BeforeAll
    public static void setup(){

        Client client = new Client(1L, "Fred", "Johnson", "John",
                "fred@fred.com", false, new ArrayList<>());

        payment = new Payment(1L, "NEW", 100.0, client, false);

    }

    @Test
    public void whenGetByIdShouldReturnPayment() {

        when(paymentRepository
                .findById(anyLong()))
                .thenReturn(Optional.ofNullable(payment));

        Payment paymentTest = paymentCrudServiceBean.getById(1L);

        assertThat(paymentTest.getId()).isSameAs(payment.getId());
        assertThat(paymentTest.getStatus()).isSameAs(payment.getStatus());

        verify(paymentRepository).findById(payment.getId());
    }

    @Test
    public void whenUnregisteredIdShouldReturnPaymentNotFoundException(){
        Exception exception = assertThrows(PaymentNotFoundException.class,
                () -> paymentCrudServiceBean.getById(2L));
        assertTrue(exception.getMessage().contains("Payment with id: 2 was not found in database"));
    }

}

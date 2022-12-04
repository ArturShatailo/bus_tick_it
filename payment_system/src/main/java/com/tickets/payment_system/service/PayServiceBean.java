package com.tickets.payment_system.service;

import com.tickets.payment_system.domain.Client;
import com.tickets.payment_system.domain.Payment;
import com.tickets.payment_system.repository.ClientRepository;
import com.tickets.payment_system.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class PayServiceBean implements PayService{

    private final PaymentRepository paymentRepository;

    private final ClientRepository clientRepository;

    @Override
    public Long do_payment(Client c, Double amount) {
        Client client = clientRepository.findClientByEmail(c.getEmail())
                .orElseThrow(() -> new NoSuchElementException("Can't find client with email: " + c.getEmail()));
        Payment payment = paymentRepository.saveAndFlush(new Payment("NEW", client, amount));
        return payment.getId();
    }
}

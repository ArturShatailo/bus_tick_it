package com.tickets.payment_system.service;

import com.tickets.payment_system.domain.Client;
import com.tickets.payment_system.domain.Payment;
import com.tickets.payment_system.repository.ClientRepository;
import com.tickets.payment_system.repository.PaymentRepository;
import com.tickets.payment_system.util.exceptions.ClientNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PayServiceBean implements PayService{

    private final PaymentRepository paymentRepository;

    private final ClientRepository clientRepository;

    @Override
    public Long do_payment(Client c, Double amount) {

        log.info("[Payment system] Start do_payment service method with Client id {} and amount {}", c.getId(), amount);

        Client client = clientRepository.findClientByEmail(c.getEmail())
                .orElseThrow(() -> new ClientNotFoundException("Can't find client with email " + c.getEmail() + " in database"));
        Payment payment = paymentRepository.saveAndFlush(new Payment("NEW", client, amount));

        log.info("[Payment system] payment with id {} is saved in database", payment.getId());

        return payment.getId();
    }
}

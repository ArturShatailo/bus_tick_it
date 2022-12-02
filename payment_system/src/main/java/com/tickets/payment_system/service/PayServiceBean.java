package com.tickets.payment_system.service;

import com.tickets.payment_system.domain.Client;
import com.tickets.payment_system.domain.Payment;
import com.tickets.payment_system.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PayServiceBean implements PayService{

    private final PaymentRepository paymentRepository;

    @Override
    public Long do_payment(Client client, Double amount) {
        Payment payment = paymentRepository.save(new Payment("NEW", client, amount));
        return payment.getId();
    }
}

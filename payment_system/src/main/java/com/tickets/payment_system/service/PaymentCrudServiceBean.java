package com.tickets.payment_system.service;

import com.tickets.payment_system.domain.Payment;
import com.tickets.payment_system.repository.PaymentRepository;
import com.tickets.payment_system.util.exceptions.PaymentNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PaymentCrudServiceBean implements CrudService<Payment> {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment getById(Long id) {
        log.info("[Payment system] Start service method getById with parameter id: {}", id);
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment with id: " + id + " was not found in database"));
    }

}

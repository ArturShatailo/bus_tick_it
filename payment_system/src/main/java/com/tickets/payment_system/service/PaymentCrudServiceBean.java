package com.tickets.payment_system.service;

import com.tickets.payment_system.domain.Payment;
import com.tickets.payment_system.repository.PaymentRepository;
import com.tickets.payment_system.util.exceptions.PaymentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentCrudServiceBean implements CrudService<Payment> {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment getById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment with id: " + id + " was not found in database"));
    }

}

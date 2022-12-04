package com.tickets.payment_system.service;

import com.tickets.payment_system.domain.Payment;
import com.tickets.payment_system.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class PaymentCrudServiceBean implements CrudService<Payment> {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment getById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find Payment with id: " + id));
    }

}

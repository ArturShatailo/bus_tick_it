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

    @Override
    public void update(Long id, Payment payment) {
        paymentRepository.findById(id).map(
                p -> {
                    p.setClient(payment.getClient());
                    p.setStatus(payment.getStatus());
                    p.setDeleted(payment.getDeleted());
                    return paymentRepository.save(p);
                }
        ).orElseThrow(() -> new NoSuchElementException("Can't find an Payment with id = " + id));
    }

    @Override
    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void delete(Long id) {
        Payment payment = paymentRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Can't find Payment with id: " + id));
        payment.setDeleted(true);
        paymentRepository.save(payment);
    }

}

package com.tickets.payment_system.service;

import com.tickets.payment_system.domain.Payment;
import com.tickets.payment_system.repository.PaymentRepository;
import com.tickets.payment_system.util.configuration.StatusConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentStatusServiceBean implements PaymentStatusService {

    private final PaymentRepository paymentRepository;

    private final StatusConfig statusConfig;

    @Override
    public String status_processing(Long payment_id) {
        return paymentRepository.findById(payment_id)
                .map(
                        p -> {
                            p.setStatus(defineRandomFromList(statusConfig.status()));
                            return paymentRepository.save(p);
                        }
                )
                .orElseThrow(() -> new NoSuchElementException("Can't find Payment with id: " + payment_id))
                .getStatus();
    }

    @Override
    public Map<Long, String> status_processing() {
        return paymentRepository.findAll().stream()
                .collect(Collectors.toMap(
                        Payment::getId,
                        v -> {
                            String status = defineRandomFromList(statusConfig.status());
                            v.setStatus(status);
                            return status;
                        }
                        ));
    }

    private String defineRandomFromList(List<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

}

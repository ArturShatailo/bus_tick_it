package com.tickets.payment_system.service;

import com.tickets.payment_system.domain.Payment;
import com.tickets.payment_system.repository.PaymentRepository;
import com.tickets.payment_system.util.configuration.StatusConfig;
import com.tickets.payment_system.util.exceptions.PaymentNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PaymentStatusServiceBean implements PaymentStatusService {

    private final PaymentRepository paymentRepository;

    private final StatusConfig statusConfig;

    @Override
    public String status_processing(Long id) {
        return paymentRepository.findById(id)
                .map(
                        p -> {
                            p.setStatus(defineRandomFromList(statusConfig.status()));
                            log.info("[Payment system] status_processing service method sets status for payment with id {}", id);
                            return paymentRepository.save(p);
                        }
                )
                .orElseThrow(() -> new PaymentNotFoundException("Payment with id: " + id + " was not found in database"))
                .getStatus();
    }

    @Override
    public Map<Long, String> status_processing() {
        return paymentRepository.findAll().stream()
                .collect(Collectors.toMap(
                        Payment::getId,
                        v -> {
                            String status = defineRandomFromList(statusConfig.status());
                            log.info("[Payment system] status_processing service method sets status for payment with id {}", v.getId());
                            v.setStatus(status);
                            return paymentRepository.save(v).getStatus();
                        }
                        ));
    }

    private String defineRandomFromList(List<String> list) {
        Random rand = new Random();
        String status = list.get(rand.nextInt(list.size()));
        log.info("[Payment system] status {} is randomly chosen", status);
        return status;
    }

}

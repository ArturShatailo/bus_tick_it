package com.tickets.payment_system.service;

import java.util.Map;

public interface PaymentStatusService {

    String status_processing(Long payment_id);

    Map<Long, String> status_processing();

}

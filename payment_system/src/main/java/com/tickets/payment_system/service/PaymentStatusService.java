package com.tickets.payment_system.service;

import java.util.Map;

public interface PaymentStatusService {

    /**
     * Receives id of a Payment and changes its status to a random one.
     * Returns a new status value of the Payment object.
     *
     * @param payment_id the Payment id
     * @return the changed Payment status
     */
    String status_processing(Long payment_id);

    /**
     * Changes statuses of all Payment objects to random ones.
     *
     * @return map of Payments ids as keys and changed Payments statuses as values.
     */
    Map<Long, String> status_processing();

}

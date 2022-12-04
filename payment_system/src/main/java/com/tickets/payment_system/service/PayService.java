package com.tickets.payment_system.service;

import com.tickets.payment_system.domain.Client;

public interface PayService {

    /**
     * Creates new Payment with amount and Client's data set in a parameters
     * and saves it in database. Returns id of the created Payment.
     *
     * @param client the Client object
     * @param amount the amount of funds for Payment
     * @return id of the created Payment
     */
    Long do_payment (Client client, Double amount);

}

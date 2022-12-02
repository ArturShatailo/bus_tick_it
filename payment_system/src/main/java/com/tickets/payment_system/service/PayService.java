package com.tickets.payment_system.service;

import com.tickets.payment_system.domain.Client;

public interface PayService {

    Long do_payment (Client client, Double amount);

}

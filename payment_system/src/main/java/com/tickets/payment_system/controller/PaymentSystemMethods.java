package com.tickets.payment_system.controller;

import com.tickets.payment_system.domain.dto.ClientDTO;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;

public interface PaymentSystemMethods {

    /**
     * Receives id of a Payment and changes the status
     * (in the current variant, this is a random status).
     * Returns a new set status.
     *
     * @param id Payment id
     * @return Status of a Payment that has been set
     */
    @GetMapping("/status/{id}")
    String setStatus(@PathVariable Long id);

    /**
     * Receives id of a Payment and returns its status value
     *
     * @param id Payment id
     * @return Status of a Payment
     */
    @GetMapping("/st/{id}")
    String getPaymentStatus(@PathVariable Long id);

    /**
     * Sets statuses for all Payment entities
     * (in this variant, statuses are randomly chosen)
     *
     * @return Returns map of id of payment as a key and status of payment as a value.
     */
    @PutMapping("/statuses")
    Map<Long, String> setStatuses();


    /**
     * Receives amount and client's information and creates new Payment with these details.
     * Saves payment in database and returns its id.
     *
     * @param clientDTO the Client DTO
     * @param amount    the amount of payment
     * @return the Payment id
     */
    @PostMapping("/{amount}")
    Long pay(@RequestBody @Valid ClientDTO clientDTO, @PathVariable Double amount);

}

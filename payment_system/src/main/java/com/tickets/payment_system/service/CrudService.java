package com.tickets.payment_system.service;

public interface CrudService<T> {

    /**
     * Returns Payment entity from database by received id parameter.
     *
     * @param id Payment id
     * @return Payment entity
     */
    T getById(Long id);
}

package com.tickets.payment_system.service;

public interface CrudService<T> {

    /**
     * Returns entity from database by received id parameter.
     *
     * @param id id of entity
     * @return Found entity
     */
    T getById(Long id);
}

package com.tickets.payment_system.service;

public interface CrudService<T> {

    T getById(Long id);

    void update(Long id, T T);

    T create(T T);

    void delete(Long id);

}

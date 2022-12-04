package com.tickets.tickets_managemet.service.route;

import java.util.List;

public interface CrudService<T> {

    T getById(Long id);

    List<T> getAllRoutes();

}

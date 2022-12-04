package com.tickets.tickets_managemet.service.route;

import java.util.List;

public interface CrudService<T> {

    /**
     * Returns entity from database by received id parameter.
     *
     * @param id id of entity
     * @return Found entity
     */
    T getById(Long id);

    /**
     * Returns List of entities from database
     *
     * @return list of all objects.
     */
    List<T> getAllRoutes();

}

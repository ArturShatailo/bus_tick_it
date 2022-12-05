package com.tickets.tickets_managemet.service.tickets_status_processor;

import java.util.Map;

public interface TicketProcessorService {

    /**
     * Checks status of payment of each Ticket and finds ones with "NEW" status. Filters them according
     * to the status, time of the last check, deleted boolean and checked boolean. Sets new statuses for
     * payments of filtered tickets randomly. Saves all the changes in database.
     * In case of any ticket receives status "FAIL", it receives checked marker and the id of route as a key and amount of available tickets
     * as a value will be added to the created map. Returns created map of routes ids and theirs
     * available tickets amounts.
     * In case of any ticket receives status "DONE", it receives checked marker.
     * Receiving status "NEW" will not provide any changes.
     *
     * @return map of id of routes (keys) and amount of available tickets on routes (values).
     */
    Map<Long, Integer> ticketsCheck();

}

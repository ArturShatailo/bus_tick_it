package com.tickets.tickets_managemet.util.exceptions.ticket;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String message) {
        super(message);
    }
}

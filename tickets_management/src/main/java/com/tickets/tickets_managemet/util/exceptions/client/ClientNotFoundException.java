package com.tickets.tickets_managemet.util.exceptions.client;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(String message) {
        super(message);
    }
}

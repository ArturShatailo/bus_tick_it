package com.tickets.tickets_managemet.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketInfo {

    public Route route;

    public String payment_status;

}

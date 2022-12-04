package com.tickets.tickets_managemet.domain.dto;

import com.tickets.tickets_managemet.domain.Route;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketInfo {

    public Route route;

    public String payment_status;

}

package com.tickets.tickets_managemet.domain.dto;

import com.tickets.tickets_managemet.domain.Route;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketInfo {

    public Route route;

    public String payment_status;

}

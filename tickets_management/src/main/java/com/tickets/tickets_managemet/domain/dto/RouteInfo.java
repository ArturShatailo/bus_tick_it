package com.tickets.tickets_managemet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteInfo {

    public Long id;

    public String from;

    public String to;

    public Date departure_time;

    public Double cost;

    public Integer available_tickets_amount;

}

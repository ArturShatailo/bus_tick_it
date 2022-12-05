package com.tickets.tickets_managemet.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "station_addresses")
public class StationAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private String city;

    private String street;

    private String building;

}

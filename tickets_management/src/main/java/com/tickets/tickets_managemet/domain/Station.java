package com.tickets.tickets_managemet.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "stations")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private StationAddress address;

}

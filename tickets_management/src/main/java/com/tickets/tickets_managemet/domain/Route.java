package com.tickets.tickets_managemet.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "routes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Station from;

    @OneToOne(cascade = CascadeType.ALL)
    private Station to;

    private String departure_time;

    private Double cost;

    private Integer available_tickets_amount;

    private Boolean deleted = Boolean.FALSE;

//    @OneToMany(mappedBy="route", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
//    List<Ticket> tickets;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    List<Ticket> bought_tickets;

    public void sellTicket() {
        available_tickets_amount -= 1;
    }

    public void backTicket() {
        available_tickets_amount += 1;
    }
}

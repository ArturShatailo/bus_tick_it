package com.tickets.tickets_managemet.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tickets")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Route route;

    @ManyToOne
    private Client client;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    private Payment payment;

    //private Long client_id;

    private Long payment_id;

    private Date last_check_date;

    private String current_status;

    private Boolean deleted = false;

    private Boolean is_checked;

}

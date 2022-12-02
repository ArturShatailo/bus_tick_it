package com.tickets.tickets_managemet.domain;

import com.tickets.clients_management.domain.Client;
import com.tickets.payment_system.domain.Payment;
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

    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;

    @ManyToOne
    //@JoinColumn(name="route_id")
    private Route route;

    @ManyToOne
    //@JoinColumn(name="route_id")
    private Client client;

    private Date last_check_date;

    private String current_status;

    private Boolean deleted = false;

    private Boolean is_checked;

}

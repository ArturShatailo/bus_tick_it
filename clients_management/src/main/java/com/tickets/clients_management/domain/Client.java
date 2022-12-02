package com.tickets.clients_management.domain;

import com.tickets.payment_system.domain.Payment;
import com.tickets.tickets_managemet.domain.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String father_name;

    private Boolean deleted = false;

    @OneToMany(mappedBy="client", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Payment> payments;

    @OneToMany(mappedBy="client", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Ticket> tickets;
}

package com.tickets.payment_system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    public String name;

    public String surname;

    public String father_name;

    public String email;

}

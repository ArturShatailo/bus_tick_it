package com.tickets.tickets_managemet.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    public String name;

    @NotNull(message = "Surname may not be null")
    @Size(min = 2, max = 32, message = "Surname must be between 2 and 32 characters long")
    public String surname;

    @NotNull(message = "Middle name (patronymic) may not be null")
    @Size(min = 2, max = 32, message = "Middle name must be between 2 and 32 characters long")
    public String father_name;

    @Email
    @NotNull(message = "Email number may not be null")
    public String email;

}

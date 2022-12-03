package com.tickets.payment_system.domain;

import io.swagger.v3.oas.annotations.media.Schema;
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

    //@NotNull(message = "Name may not be null")
    //@Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Schema(description = "Name of a client", example = "Martin", required = true)
    public String name;

    //@NotNull(message = "Surname may not be null")
    //@Size(min = 2, max = 32, message = "Surname must be between 2 and 32 characters long")
    @Schema(description = "Surname of a client.", example = "Jefferson", required = true)
    public String surname;

    //@NotNull(message = "Middle name (patronymic) may not be null")
    //@Size(min = 2, max = 32, message = "Middle name must be between 2 and 32 characters long")
    @Schema(description = "Middle name (patronymic) of a client.", example = "Jeffrey", required = true)
    public String father_name;

    @Email
    @NotNull(message = "Email number may not be null")
    @Schema(description = "Email address of a client.", example = "martinJefferson@mail.com", required = true)
    public String email;

}

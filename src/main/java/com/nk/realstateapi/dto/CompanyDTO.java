package com.nk.realstateapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyDTO {

    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String name;

    @NotBlank(message = "El tipo no puede estar vacío")
    @Size(max = 50, message = "El tipo no puede tener más de 50 caracteres")
    private String type;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(max = 200, message = "La dirección no puede tener más de 200 caracteres")
    private String address;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe tener un formato válido")
    private String email;
}

package com.nk.realstateapi.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nk.realstateapi.entities.Promotion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HousingDTO {

    private Integer id;

    @NotNull(message = "La superficie no puede ser nula")
    @Positive(message = "La superficie debe ser un número positivo")
    private Double surface;

    @NotNull(message = "El número de dormitorios no puede ser nulo")
    @Min(value = 0, message = "Debe tener al menos 0 dormitorios")
    private Integer bedrooms;

    @NotNull(message = "El número de baños no puede ser nulo")
    @Min(value = 0, message = "Debe tener al menos 0 baños")
    private Integer bathrooms;

    @NotBlank(message = "La foto no puede estar vacía")
    private String foto;

    @NotNull(message = "El precio no puede ser nulo")
    @Positive(message = "El precio debe ser un número positivo")
    private Double price;

    @NotNull(message = "La información sobre la terraza no puede ser nula")
    private Boolean terrace;

    @NotNull(message = "La información sobre el jardín no puede ser nula")
    private Boolean garden;

    @NotNull(message = "La información sobre la piscina no puede ser nula")
    private Boolean pool;

    @NotNull(message = "La información sobre el garaje no puede ser nula")
    private Boolean garage;

    private PromotionDTO promotion;

}

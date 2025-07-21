package com.nk.realstateapi.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nk.realstateapi.entities.Promotion;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PopulationDTO {

    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotNull(message = "La promoción no puede ser nula")
    private PromotionDTO promotion;

}

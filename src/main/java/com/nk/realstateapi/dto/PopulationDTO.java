package com.nk.realstateapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

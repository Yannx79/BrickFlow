package com.nk.realstateapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromotionDTO {

    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    // @NotNull(message = "La lista de poblaciones no puede ser nula")
    @Valid
    private List<@Valid PopulationDTO> populations;

    // @NotNull(message = "La lista de viviendas no puede ser nula")
    @Valid
    private List<@Valid HousingDTO> housings;

}

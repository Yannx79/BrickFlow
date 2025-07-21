package com.nk.realstateapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nk.realstateapi.entities.Housing;
import com.nk.realstateapi.entities.Population;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

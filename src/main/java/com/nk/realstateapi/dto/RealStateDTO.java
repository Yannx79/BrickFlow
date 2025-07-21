package com.nk.realstateapi.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nk.realstateapi.entities.Company;
import com.nk.realstateapi.entities.Promotion;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RealStateDTO {

    private Integer Id;

    // @NotNull(message = "La promoción no puede ser nula")
    @Valid
    private PromotionDTO promotion;

    // @NotNull(message = "La empresa no puede ser nula")
    @Valid
    private CompanyDTO company;

}

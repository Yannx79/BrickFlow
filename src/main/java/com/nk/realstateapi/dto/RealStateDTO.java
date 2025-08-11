package com.nk.realstateapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

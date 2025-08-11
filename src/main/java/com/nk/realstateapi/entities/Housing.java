package com.nk.realstateapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Housing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer Id;

    private Double surface;

    private Integer bedrooms;

    private Integer bathrooms;

    private String foto;

    private Double price;

    private Boolean terrace;

    private Boolean garden;

    private Boolean pool;

    private Boolean garage;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

}

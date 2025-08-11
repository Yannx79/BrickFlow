package com.nk.realstateapi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(length = 60)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Population> populations;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Housing> housings;

}

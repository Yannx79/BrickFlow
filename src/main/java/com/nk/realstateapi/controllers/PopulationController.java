package com.nk.realstateapi.controllers;

import com.nk.realstateapi.dto.PopulationDTO;
import com.nk.realstateapi.entities.Population;
import com.nk.realstateapi.services.IPopulationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/population")
@RequiredArgsConstructor
public class PopulationController {

    private final IPopulationService service;

    @Qualifier("populationModelMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PopulationDTO>> readAll() throws Exception {
        List<PopulationDTO> list = service.readAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PopulationDTO> readById(@PathVariable Integer id) throws Exception {
        PopulationDTO dto = convertToDTO(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PopulationDTO> create(@Valid @RequestBody PopulationDTO dto) throws Exception {
        Population entity = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDTO(entity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PopulationDTO> update(@PathVariable("id") Integer id, @RequestBody @Valid PopulationDTO dto) throws Exception {
        dto.setId(id);
        Population entity = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDTO(entity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private PopulationDTO convertToDTO(Population entity) {
        return mapper.map(entity, PopulationDTO.class);
    }

    private Population convertToEntity(PopulationDTO dto) {
        return mapper.map(dto, Population.class);
    }

}

package com.nk.realstateapi.controllers;

import com.nk.realstateapi.dto.HousingDTO;
import com.nk.realstateapi.entities.Housing;
import com.nk.realstateapi.services.IHousingService;
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
@RequestMapping("/housing")
@RequiredArgsConstructor
public class HousingController {

    private final IHousingService service;

    @Qualifier("housingModelMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<HousingDTO>> readAll() throws Exception {
        List<HousingDTO> list = service.readAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HousingDTO> readById(@PathVariable Integer id) throws Exception {
        HousingDTO dto = convertToDTO(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HousingDTO> create(@Valid @RequestBody HousingDTO dto) throws Exception {
        Housing entity = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDTO(entity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HousingDTO> update(@PathVariable("id") Integer id, @RequestBody @Valid HousingDTO dto) throws Exception {
        dto.setId(id);
        Housing entity = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDTO(entity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private HousingDTO convertToDTO(Housing entity) {
        return mapper.map(entity, HousingDTO.class);
    }

    private Housing convertToEntity(HousingDTO dto) {
        return mapper.map(dto, Housing.class);
    }

}

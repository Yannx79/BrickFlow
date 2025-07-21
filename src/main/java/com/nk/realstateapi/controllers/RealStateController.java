package com.nk.realstateapi.controllers;

import com.nk.realstateapi.dto.RealStateDTO;
import com.nk.realstateapi.entities.RealState;
import com.nk.realstateapi.services.IRealStateService;
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
@RequestMapping("/real-states")
@RequiredArgsConstructor
public class RealStateController {

    private final IRealStateService service;

    @Qualifier("realStateModelMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<RealStateDTO>> readAll() throws Exception {
        List<RealStateDTO> list = service.readAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RealStateDTO> readById(@PathVariable Integer id) throws Exception {
        RealStateDTO dto = convertToDTO(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RealStateDTO> create(@Valid @RequestBody RealStateDTO dto) throws Exception {
        RealState entity = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDTO(entity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RealStateDTO> update(@PathVariable("id") Integer id, @RequestBody @Valid RealStateDTO dto) throws Exception {
        dto.setId(id);
        RealState entity = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDTO(entity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private RealStateDTO convertToDTO(RealState entity) {
        return mapper.map(entity, RealStateDTO.class);
    }

    private RealState convertToEntity(RealStateDTO dto) {
        return mapper.map(dto, RealState.class);
    }

}

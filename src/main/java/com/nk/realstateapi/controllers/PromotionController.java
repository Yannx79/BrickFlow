package com.nk.realstateapi.controllers;

import com.nk.realstateapi.dto.PromotionDTO;
import com.nk.realstateapi.entities.Promotion;
import com.nk.realstateapi.services.IPromotionService;
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
@RequestMapping("/promotions")
@RequiredArgsConstructor
public class PromotionController {

    private final IPromotionService service;

    @Qualifier("promotionModelMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PromotionDTO>> readAll() throws Exception {
        List<PromotionDTO> list = service.readAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromotionDTO> readById(@PathVariable Integer id) throws Exception {
        PromotionDTO dto = convertToDTO(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PromotionDTO> create(@Valid @RequestBody PromotionDTO dto) throws Exception {
        Promotion entity = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDTO(entity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PromotionDTO> update(@PathVariable("id") Integer id, @RequestBody @Valid PromotionDTO dto) throws Exception {
        dto.setId(id);
        Promotion entity = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDTO(entity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private PromotionDTO convertToDTO(Promotion entity) {
        return mapper.map(entity, PromotionDTO.class);
    }

    private Promotion convertToEntity(PromotionDTO dto) {
        return mapper.map(dto, Promotion.class);
    }

}

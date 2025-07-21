package com.nk.realstateapi.controllers;

import com.nk.realstateapi.dto.CompanyDTO;
import com.nk.realstateapi.entities.Company;
import com.nk.realstateapi.services.ICompanyService;
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
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final ICompanyService service;

    @Qualifier("companyModelMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> readAll() throws Exception {
        List<CompanyDTO> list = service.readAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> readById(@PathVariable Integer id) throws Exception {
        CompanyDTO dto = convertToDTO(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> create(@Valid @RequestBody CompanyDTO dto) throws Exception {
        Company entity = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDTO(entity), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> update(@PathVariable("id") Integer id, @RequestBody @Valid CompanyDTO dto) throws Exception {
        dto.setId(id);
        Company entity = service.update(convertToEntity(dto), id);
        return new ResponseEntity<>(convertToDTO(entity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private CompanyDTO convertToDTO(Company entity) {
        return mapper.map(entity, CompanyDTO.class);
    }

    private Company convertToEntity(CompanyDTO dto) {
        return mapper.map(dto, Company.class);
    }

}

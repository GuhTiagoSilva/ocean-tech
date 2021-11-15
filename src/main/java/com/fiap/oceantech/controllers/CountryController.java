package com.fiap.oceantech.controllers;

import com.fiap.oceantech.dto.CountryDTO;
import com.fiap.oceantech.entities.custom.CountryProjection;
import com.fiap.oceantech.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping
    public ResponseEntity<CountryDTO> insert(@RequestBody CountryDTO countryDTO) {
        countryDTO = countryService.insert(countryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(countryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryDTO> update (@PathVariable Long id, @RequestBody CountryDTO countryDTO) {
        countryDTO = countryService.update(id, countryDTO);
        return ResponseEntity.status(HttpStatus.OK).body(countryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        countryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> findById(@PathVariable Long id) {
        CountryDTO countryDTO = countryService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(countryDTO);
    }

    @GetMapping
    public ResponseEntity<Page<CountryDTO>> findAll(Pageable pageable) {
        Page<CountryDTO> page = countryService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/notPaged")
    public ResponseEntity<List<CountryDTO>> findAll() {
        List<CountryDTO> countries = countryService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(countries);
    }

    @GetMapping("/oceans")
    public ResponseEntity<List<CountryProjection>> getCountryAmountByOcean() {
        List<CountryProjection> countries = countryService.getCountryAmountByOcean();
        return ResponseEntity.status(HttpStatus.OK).body(countries);
    }
}

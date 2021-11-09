package com.fiap.oceantech.controllers;

import com.fiap.oceantech.dto.OceanDataDTO;
import com.fiap.oceantech.services.OceanDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oceanData")
public class OceanDataController {

    @Autowired
    private OceanDataService oceanService;

    @PostMapping
    public ResponseEntity<OceanDataDTO> insert(@RequestBody OceanDataDTO oceanDTO) {
        oceanDTO = oceanService.insert(oceanDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(oceanDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OceanDataDTO> update (@PathVariable Long id, @RequestBody OceanDataDTO oceanDTO) {
        oceanDTO = oceanService.update(id, oceanDTO);
        return ResponseEntity.status(HttpStatus.OK).body(oceanDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        oceanService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OceanDataDTO> findById(@PathVariable Long id) {
        OceanDataDTO oceanDTO = oceanService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(oceanDTO);
    }

    @GetMapping
    public ResponseEntity<Page<OceanDataDTO>> findAll(Pageable pageable) {
        Page<OceanDataDTO> page = oceanService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
}

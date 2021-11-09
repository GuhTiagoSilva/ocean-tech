package com.fiap.oceantech.controllers;

import com.fiap.oceantech.dto.OceanDTO;
import com.fiap.oceantech.entities.custom.OceanProjection;
import com.fiap.oceantech.services.OceanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oceans")
public class OceanController {

    @Autowired
    private OceanService oceanService;

    @PostMapping
    public ResponseEntity<OceanDTO> insert(@RequestBody OceanDTO oceanDTO) {
        oceanDTO = oceanService.insert(oceanDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(oceanDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OceanDTO> update (@PathVariable Long id, @RequestBody OceanDTO oceanDTO) {
        oceanDTO = oceanService.update(id, oceanDTO);
        return ResponseEntity.status(HttpStatus.OK).body(oceanDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        oceanService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OceanDTO> findById(@PathVariable Long id) {
        OceanDTO oceanDTO = oceanService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(oceanDTO);
    }

    @GetMapping
    public ResponseEntity<Page<OceanDTO>> findAll(Pageable pageable) {
        Page<OceanDTO> page = oceanService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/temperature")
    public ResponseEntity<List<OceanProjection>> getMaxTemperatureByOcean() {
        List<OceanProjection> oceans = oceanService.getMaxTemperatureByOcean();
        return ResponseEntity.status(HttpStatus.OK).body(oceans);
    }
}

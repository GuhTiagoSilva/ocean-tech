package com.fiap.oceantech.services;

import com.fiap.oceantech.dto.OceanDTO;
import com.fiap.oceantech.entities.Ocean;
import com.fiap.oceantech.entities.custom.OceanProjection;
import com.fiap.oceantech.exceptions.DatabaseException;
import com.fiap.oceantech.exceptions.ResourceNotFoundException;
import com.fiap.oceantech.repositories.OceanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OceanService {

    @Autowired
    private OceanRepository oceanRepository;

    @Transactional
    public OceanDTO insert(OceanDTO oceanDTO) {
        Ocean entity = new Ocean();
        copyDtoToEntity(oceanDTO, entity);
        oceanRepository.save(entity);
        return new OceanDTO(entity);
    }

    @Transactional(readOnly = true)
    public OceanDTO findById(Long id) {
        Optional<Ocean> result = oceanRepository.findById(id);
        Ocean entity = result.orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        return new OceanDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<OceanDTO> findAll(Pageable pageable) {
        Page<Ocean> page = oceanRepository.findAll(pageable);
        return page.map(ocean -> new OceanDTO(ocean));
    }

    @Transactional(readOnly = true)
    public List<OceanDTO> findAll() {
        List<Ocean> oceans = oceanRepository.findAll();
        return oceans.stream().map(ocean -> new OceanDTO(ocean)).collect(Collectors.toList());
    }

    @Transactional
    public OceanDTO update(Long id, OceanDTO oceanDTO) {
        try {
            Ocean entity = oceanRepository.getById(id);
            copyDtoToEntity(oceanDTO, entity);
            return new OceanDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Entity not found");
        }
    }

    @Transactional(readOnly = true)
    public List<OceanProjection> getMaxTemperatureByOcean() {
        return oceanRepository.getMaxTemperatureByOcean();
    }

    public void delete(Long id) {
        try {
            oceanRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Entity not found");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database Exception");
        }
    }

    private void copyDtoToEntity (OceanDTO oceanDTO, Ocean entity) {
        entity.setName(oceanDTO.getName());
    }

}

package com.fiap.oceantech.services;

import com.fiap.oceantech.dto.OceanDataDTO;
import com.fiap.oceantech.entities.Ocean;
import com.fiap.oceantech.entities.OceanData;
import com.fiap.oceantech.exceptions.DatabaseException;
import com.fiap.oceantech.exceptions.ResourceNotFoundException;
import com.fiap.oceantech.repositories.OceanDataRepository;
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
public class OceanDataService {

    @Autowired
    private OceanDataRepository oceanDataRepository;

    @Autowired
    private OceanRepository oceanRepository;

    @Transactional
    public OceanDataDTO insert(OceanDataDTO oceanDTO) {
        OceanData entity = new OceanData();
        copyDtoToEntity(oceanDTO, entity);
        oceanDataRepository.save(entity);
        return new OceanDataDTO(entity);
    }

    @Transactional(readOnly = true)
    public OceanDataDTO findById(Long id) {
        Optional<OceanData> result = oceanDataRepository.findById(id);
        OceanData entity = result.orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        return new OceanDataDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<OceanDataDTO> findAll(Pageable pageable) {
        Page<OceanData> page = oceanDataRepository.findAll(pageable);
        return page.map(ocean -> new OceanDataDTO(ocean));
    }

    @Transactional(readOnly = true)
    public List<OceanDataDTO> findAll() {
        List<OceanData> oceans = oceanDataRepository.findAll();
        return oceans.stream().map(ocean -> new OceanDataDTO(ocean)).collect(Collectors.toList());
    }

    @Transactional
    public OceanDataDTO update(Long id, OceanDataDTO oceanDTO) {
        try {
            OceanData entity = oceanDataRepository.getById(id);
            copyDtoToEntity(oceanDTO, entity);
            return new OceanDataDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Entity not found");
        }
    }

    public void delete(Long id) {
        try {
            oceanDataRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Entity not found");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database Exception");
        }
    }

    private void copyDtoToEntity(OceanDataDTO dto, OceanData entity) {
        entity.setPh(dto.getPh());
        entity.setStartDate(dto.getStartDate());
        entity.setTemperature(dto.getTemperature());
        entity.setWeekDay(dto.getWeekDay());

        Ocean ocean = new Ocean();

        if (dto.getOceanId() != null){
            ocean = oceanRepository.getById(dto.getOceanId());
        }

        entity.setOcean(ocean);

    }

}

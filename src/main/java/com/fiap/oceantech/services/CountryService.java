package com.fiap.oceantech.services;

import com.fiap.oceantech.dto.CountryDTO;
import com.fiap.oceantech.dto.OceanDTO;
import com.fiap.oceantech.entities.Country;
import com.fiap.oceantech.entities.Ocean;
import com.fiap.oceantech.entities.custom.CountryProjection;
import com.fiap.oceantech.exceptions.DatabaseException;
import com.fiap.oceantech.exceptions.ResourceNotFoundException;
import com.fiap.oceantech.repositories.CountryRepository;
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
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private OceanRepository oceanRepository;


    @Transactional
    public CountryDTO insert(CountryDTO countryDTO) {
        Country entity = new Country();
        copyDtoToEntity(countryDTO, entity);
        countryRepository.save(entity);
        return new CountryDTO(entity);
    }

    @Transactional(readOnly = true)
    public CountryDTO findById(Long id) {
        Optional<Country> result = countryRepository.findById(id);
        Country entity = result.orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        return new CountryDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<CountryDTO> findAll(Pageable pageable) {
        Page<Country> page = countryRepository.findAll(pageable);
        return page.map(country -> new CountryDTO(country));
    }

    @Transactional(readOnly = true)
    public List<CountryDTO> findAll() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream().map(country -> new CountryDTO(country)).collect(Collectors.toList());
    }

    @Transactional
    public CountryDTO update(Long id, CountryDTO countryDTO) {
        try {
            Country entity = countryRepository.getById(id);
            copyDtoToEntity(countryDTO, entity);
            return new CountryDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Entity not found");
        }
    }

    public void delete(Long id) {
        try {
            countryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Entity not found");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Database Exception");
        }
    }

    @Transactional(readOnly = true)
    public List<CountryProjection> getCountryAmountByOcean() {
        return countryRepository.getCountryAmountByOcean();
    }

    private void copyDtoToEntity(CountryDTO countryDTO, Country entity) {
        entity.setName(countryDTO.getName());
        entity.setPopulation(countryDTO.getPopulation());

        entity.getOceans().clear();
        for (OceanDTO oceanDTO : countryDTO.getOceans()) {
            Ocean ocean = oceanRepository.getById(oceanDTO.getId());
            entity.getOceans().add(ocean);
        }
    }


}

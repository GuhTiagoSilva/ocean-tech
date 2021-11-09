package com.fiap.oceantech.dto;

import com.fiap.oceantech.entities.Country;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CountryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String population;
    private List<OceanDTO> oceans = new ArrayList<>();

    public CountryDTO() {

    }

    public CountryDTO(Long id, String name, String population) {
        this.id = id;
        this.name = name;
        this.population = population;
    }

    public CountryDTO(Country entity) {
        id = entity.getId();
        name = entity.getName();
        population = entity.getPopulation();
        entity.getOceans().forEach(ocean -> oceans.add(new OceanDTO(ocean)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public List<OceanDTO> getOceans() {
        return oceans;
    }
}

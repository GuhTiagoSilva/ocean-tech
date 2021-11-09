package com.fiap.oceantech.dto;

import com.fiap.oceantech.entities.Ocean;

import java.io.Serializable;

public class OceanDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    public OceanDTO() {

    }

    public OceanDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public OceanDTO(Ocean entity) {
        id = entity.getId();
        name = entity.getName();
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
}

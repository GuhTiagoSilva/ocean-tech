package com.fiap.oceantech.dto;

import com.fiap.oceantech.entities.OceanData;
import com.fiap.oceantech.entities.enums.WeekDay;

import java.time.Instant;

public class OceanDataDTO {

    private Long id;
    private Double ph;
    private Double temperature;
    private Instant startDate;
    private WeekDay weekDay;
    private Long oceanId;

    public OceanDataDTO() {

    }

    public OceanDataDTO(Long id, Double ph, Double temperature, Instant startDate, WeekDay weekDay, Long oceanId) {
        this.id = id;
        this.ph = ph;
        this.temperature = temperature;
        this.startDate = startDate;
        this.weekDay = weekDay;
        this.oceanId = oceanId;
    }

    public OceanDataDTO(OceanData entity) {
        id = entity.getId();
        ph = entity.getPh();
        temperature = entity.getTemperature();
        startDate = entity.getStartDate();
        weekDay = entity.getWeekDay();
        oceanId = entity.getOcean().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public Long getOceanId() {
        return oceanId;
    }

    public void setOceanId(Long oceanId) {
        this.oceanId = oceanId;
    }
}

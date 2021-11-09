package com.fiap.oceantech.entities;

import com.fiap.oceantech.entities.enums.WeekDay;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tb_ocean_data")
public class OceanData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double ph;
    private Double temperature;
    private Instant startDate;

    @Enumerated(EnumType.STRING)
    private WeekDay weekDay;

    @ManyToOne
    @JoinColumn(name = "ocean_id")
    private Ocean ocean;

    public OceanData() {

    }

    public OceanData(Long id, Double ph, Double temperature, Instant startDate, Ocean ocean, WeekDay weekDay) {
        this.id = id;
        this.ph = ph;
        this.temperature = temperature;
        this.startDate = startDate;
        this.ocean = ocean;
        this.weekDay = weekDay;
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

    public Ocean getOcean() {
        return ocean;
    }

    public void setOcean(Ocean ocean) {
        this.ocean = ocean;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }
}

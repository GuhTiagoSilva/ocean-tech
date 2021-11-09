package com.fiap.oceantech.entities.custom;

import java.time.Instant;

public interface OceanProjection {

    Double getMaxTemperature();
    String getOcean();
    Instant getPeriod();

}

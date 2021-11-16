package com.fiap.oceantech.entities.custom;

import java.time.Instant;

public interface OceanPhProjection {

    String getOcean();
    Double getPh();
    Instant getPeriod();
    Double getTemperature();

}

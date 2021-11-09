package com.fiap.oceantech.repositories;

import com.fiap.oceantech.entities.Ocean;
import com.fiap.oceantech.entities.custom.OceanProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OceanRepository extends JpaRepository<Ocean, Long> {

    @Query(value = "" +
            "select max(od.temperature) as maxTemperature, o.name as ocean, od.start_date as period from public.tb_ocean as o " +
            "inner join public.tb_ocean_data as od " +
            "on o.id = od.ocean_id " +
            "group by o.name, od.start_date;", nativeQuery = true)
    List<OceanProjection> getMaxTemperatureByOcean();

}

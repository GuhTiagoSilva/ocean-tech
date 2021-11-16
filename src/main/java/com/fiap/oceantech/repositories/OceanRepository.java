package com.fiap.oceantech.repositories;

import com.fiap.oceantech.entities.Ocean;
import com.fiap.oceantech.entities.custom.OceanPhProjection;
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

    @Query(value = "" +
            "select distinct o.name as ocean, od.ph as ph, od.start_date as period, temperature as temperature from public.tb_ocean as o " +
            "inner join tb_ocean_data as od " +
            "on o.id = od.ocean_id " +
            "where od.start_date >= current_date and od.start_date < current_date + interval '1 day' ", nativeQuery = true)
    List<OceanPhProjection> getCurrentPh();




}

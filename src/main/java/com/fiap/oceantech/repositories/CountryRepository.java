package com.fiap.oceantech.repositories;

import com.fiap.oceantech.entities.Country;
import com.fiap.oceantech.entities.custom.CountryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query(value = "" +
            "select count(c.*) as countries, o.name as ocean, o.image_path as imagePath from public.tb_country as c " +
            "inner join public.tb_ocean_country as co " +
            "on c.id = co.country_id " +
            "inner join tb_ocean as o " +
            "on o.id = co.ocean_id " +
            "group by o.name, o.image_path;", nativeQuery = true)
    List<CountryProjection> getCountryAmountByOcean();

}

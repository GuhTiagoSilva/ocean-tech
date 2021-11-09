package com.fiap.oceantech.repositories;

import com.fiap.oceantech.entities.OceanData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OceanDataRepository extends JpaRepository<OceanData, Long> {

}

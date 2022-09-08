package com.projetct.restapi.repository;

import com.projetct.restapi.entity.EntregaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepositoty extends JpaRepository<EntregaEntity, Long> {
}

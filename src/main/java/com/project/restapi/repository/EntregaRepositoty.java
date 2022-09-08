package com.project.restapi.repository;

import com.project.restapi.model.entity.EntregaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepositoty extends JpaRepository<EntregaEntity, Long> {
}

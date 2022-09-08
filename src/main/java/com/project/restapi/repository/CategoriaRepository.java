package com.project.restapi.repository;

import com.project.restapi.model.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    CategoriaEntity findByDescricao(String descricao);

}

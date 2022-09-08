package com.project.restapi.repository;

import com.project.restapi.model.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    @Query("select p from ProdutoEntity p join CategoriaEntity c on p.categoriaEntity.id = c.id" +
            " where c.id =:categoriaId")
    List<ProdutoEntity> getProdutoByCategoriaId(@Param("categoriaId") Long categoriaId);

    @Query("select p from ProdutoEntity p where p.descricao =:descricao")
    ProdutoEntity getProdutofindByDescricao(@Param("descricao") String descricao);
}

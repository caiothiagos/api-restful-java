package com.project.restapi.model.entity;

import com.project.restapi.model.entity.dto.CategoriaDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_categoria")
@Getter
@Setter
@NoArgsConstructor
public class CategoriaEntity implements Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String descricao;

    public CategoriaEntity(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static CategoriaEntity converter(CategoriaDTO categoriaDTO){
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(categoriaDTO.getId());
        categoriaEntity.setDescricao(categoriaDTO.getDescricao());
        return categoriaEntity;
    }
}

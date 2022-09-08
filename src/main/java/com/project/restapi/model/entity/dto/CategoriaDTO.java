package com.project.restapi.model.entity.dto;

import com.project.restapi.model.entity.CategoriaEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CategoriaDTO implements Serializable {

    private Long id;
    @NotBlank
    private String descricao;

    public static CategoriaDTO converter(CategoriaEntity categoriaEntity){
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(categoriaEntity.getId());
        categoriaDTO.setDescricao(categoriaEntity.getDescricao());
        return categoriaDTO;
    }

}

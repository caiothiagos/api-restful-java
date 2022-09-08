package com.project.restapi.model.entity.dto;

import com.project.restapi.model.entity.ProdutoEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProdutoDTO implements Serializable {

    private Long id;
    @NotBlank
    private String descricao;
    @NotBlank
    private Double preco;
    @NonNull
    private CategoriaDTO categoria;

    public ProdutoDTO(String descricao, Double preco, @NonNull CategoriaDTO categoria) {
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public static ProdutoDTO convert(ProdutoEntity produtoEntity){
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produtoEntity.getId());
        produtoDTO.setDescricao(produtoEntity.getDescricao());
        produtoDTO.setPreco(produtoEntity.getPreco());
        if(produtoEntity.getCategoriaEntity() != null){
            produtoDTO.setCategoria(CategoriaDTO.converter(produtoEntity.getCategoriaEntity()));
        }
        return produtoDTO;
    }
}

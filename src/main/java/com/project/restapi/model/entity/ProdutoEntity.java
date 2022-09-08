package com.project.restapi.model.entity;

import com.project.restapi.model.entity.dto.ProdutoDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_produto")
@Getter
@Setter
@NoArgsConstructor
public class ProdutoEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    private Double preco;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaEntity categoriaEntity;

    public ProdutoEntity(Long id, String descricao, Double preco, CategoriaEntity categoriaEntity) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.categoriaEntity = getCategoriaEntity();
    }

    public static ProdutoEntity converter(ProdutoDTO produtoDTO){
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(produtoDTO.getId());
        produtoEntity.setDescricao(produtoDTO.getDescricao());
        produtoEntity.setPreco(produtoDTO.getPreco());
        if(produtoDTO.getCategoria() != null){
            produtoEntity.setCategoriaEntity(CategoriaEntity.converter(produtoDTO.getCategoria()));
        }
        return produtoEntity;
    }

}

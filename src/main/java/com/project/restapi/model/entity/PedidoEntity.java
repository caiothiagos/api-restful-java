package com.project.restapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity {


    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private UsuarioEntity usuarioEntity;


    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produtoEntity;

}

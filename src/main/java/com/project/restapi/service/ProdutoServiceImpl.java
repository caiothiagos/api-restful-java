package com.project.restapi.service;

import com.project.restapi.exception.NegocioException;
import com.project.restapi.exception.ResourceNotFoundException;
import com.project.restapi.model.entity.ProdutoEntity;
import com.project.restapi.model.entity.dto.CategoriaDTO;
import com.project.restapi.model.entity.dto.ProdutoDTO;
import com.project.restapi.model.entity.dto.request.ProdutoDTORequest;
import com.project.restapi.repository.CategoriaRepository;
import com.project.restapi.repository.ProdutoRepository;
import com.project.restapi.model.entity.CategoriaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public ProdutoDTO save(ProdutoDTORequest produtoDTO) {
        ProdutoEntity produtoEntity = null;
        Optional<CategoriaEntity> categoriaEntity = null;
        if(produtoDTO != null){
            categoriaEntity = this.categoriaRepository.findById(produtoDTO.getCategoria());
            ProdutoDTO dto = this.findByDescricao(produtoDTO.getDescricao());
            if(dto != null){
                throw new NegocioException("Produto já existe.");
            }
            dto = new ProdutoDTO(produtoDTO.getDescricao(), produtoDTO.getPreco(), CategoriaDTO.converter(categoriaEntity.get()));
            produtoEntity = this.produtoRepository.save(ProdutoEntity.converter(dto));
        }
        return ProdutoDTO.convert(produtoEntity);
    }

    @Override
    public void delete(Long id) {
        ProdutoEntity produtoEntity = this.produtoRepository.getReferenceById(id);
        if(produtoEntity != null){
            this.produtoRepository.delete(produtoEntity);
        }
    }

    @Override
    public ProdutoDTO edit(ProdutoDTO produtoDTO) {
        ProdutoEntity produtoEntity = null;
        if(produtoDTO != null){
            produtoEntity = ProdutoEntity.converter(findById(produtoDTO.getId()));
            if(produtoEntity != null){
                produtoEntity = this.produtoRepository.save(ProdutoEntity.converter(produtoDTO));
            }
            return ProdutoDTO.convert(produtoEntity);
        }
        throw new NegocioException("Aconteceu algum problema ao editar.");
    }

    @Override
    public List<ProdutoDTO> findAll() {
        List<ProdutoEntity> produtos = this.produtoRepository.findAll();
        return produtos.stream().map(ProdutoDTO::convert).collect(Collectors.toList());
    }

    @Override
    public List<ProdutoDTO> findAllByCategoriaId(Long id) {
        List<ProdutoEntity> produtos = this.produtoRepository.getProdutoByCategoriaId(id);
        return produtos.stream().map(ProdutoDTO::convert).collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO findById(Long id) {
        Optional<ProdutoEntity> produto = this.produtoRepository.findById(id);
        if(produto.isPresent()){
            return ProdutoDTO.convert(produto.get());
        }
        throw new ResourceNotFoundException("Produto com ID informado não encontrado.");
    }

    @Override
    public ProdutoDTO findByDescricao(String descricao) {
        ProdutoEntity produtoEntity = this.produtoRepository.getProdutofindByDescricao(descricao);
        if(produtoEntity != null){
            return ProdutoDTO.convert(produtoEntity);
        }
        return null;
    }

}

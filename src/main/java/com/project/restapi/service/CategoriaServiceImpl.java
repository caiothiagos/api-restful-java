package com.project.restapi.service;

import com.project.restapi.exception.NegocioException;
import com.project.restapi.exception.ResourceNotFoundException;
import com.project.restapi.repository.CategoriaRepository;
import com.project.restapi.model.entity.CategoriaEntity;
import com.project.restapi.model.entity.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        CategoriaEntity categoriaEntity = null;
        if(categoriaDTO != null){
            CategoriaDTO dto = this.findByDescricao(categoriaDTO.getDescricao());
            if(dto != null){
                throw new NegocioException("Categoria já existe.");
            }
            categoriaEntity = this.categoriaRepository.save(CategoriaEntity.converter(categoriaDTO));
        }
        return CategoriaDTO.converter(categoriaEntity);
    }

    @Override
    public void delete(CategoriaDTO categoriaDTO) {
        if(categoriaDTO != null){
            this.categoriaRepository.delete(CategoriaEntity.converter(categoriaDTO));
        }
        throw new NegocioException("Categoria não existe.");
    }

    @Override
    public CategoriaDTO edit(CategoriaDTO categoriaDTO) {
        CategoriaEntity categoriaEntity = null;
        if(categoriaDTO != null){
            categoriaEntity = this.categoriaRepository.save(CategoriaEntity.converter(categoriaDTO));
        }
        return CategoriaDTO.converter(categoriaEntity);
    }

    @Override
    public List<CategoriaDTO> findAll() {
        List<CategoriaEntity> categorias = this.categoriaRepository.findAll();
        return categorias.stream().map(CategoriaDTO::converter).collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO findById(Long id) {
        Optional<CategoriaEntity> categoria = this.categoriaRepository.findById(id);
        if(categoria.isPresent()){
            return CategoriaDTO.converter(categoria.get());
        }
        throw new ResourceNotFoundException("Categoria não encontrada.");
    }

    @Override
    public CategoriaDTO findByDescricao(String descricao) {
        CategoriaEntity categoriaEntity = this.categoriaRepository.findByDescricao(descricao);
        if(categoriaEntity != null){
            return CategoriaDTO.converter(categoriaEntity);
        }
        throw new ResourceNotFoundException("Categoria não encontrada.");
    }
}

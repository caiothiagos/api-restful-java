package com.project.restapi.service;

import com.project.restapi.model.entity.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaService {

    public CategoriaDTO save(CategoriaDTO categoriaDTO);
    public void delete(CategoriaDTO categoriaDTO);
    public CategoriaDTO edit(CategoriaDTO categoriaDTO);
    public List<CategoriaDTO> findAll();
    public CategoriaDTO findById(Long id);
    public CategoriaDTO findByDescricao(String descricao);

}

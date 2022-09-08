package com.project.restapi.service;

import com.project.restapi.model.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public List<UsuarioEntity> findAll();
    public Optional<UsuarioEntity> find(Long id);
    public UsuarioEntity create(UsuarioEntity usuarioEntity);

}

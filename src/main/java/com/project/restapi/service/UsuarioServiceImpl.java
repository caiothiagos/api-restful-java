package com.project.restapi.service;

import com.project.restapi.repository.UsuarioRepository;
import com.project.restapi.model.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        super();
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioEntity> findAll() {
        return (List<UsuarioEntity>) this.usuarioRepository.findAll();
    }

    @Override
    public Optional<UsuarioEntity> find(Long id) {
        return this.usuarioRepository.findById(id);
    }

    @Override
    public UsuarioEntity create(UsuarioEntity usuarioEntity) {
        return this.usuarioRepository.save(usuarioEntity);
    }

}

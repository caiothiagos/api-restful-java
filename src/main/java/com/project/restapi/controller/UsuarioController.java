package com.project.restapi.controller;

import com.project.restapi.model.entity.dto.UsuarioAuthenticationDTO;
import com.project.restapi.model.entity.UsuarioEntity;
import com.project.restapi.service.TokenService;
import com.project.restapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;
    private TokenService tokenService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, TokenService tokenService) {
        super();
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> findAll() {
        List<UsuarioEntity> list = this.usuarioService.findAll();
        return new ResponseEntity<List>(list, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody UsuarioEntity usuarioEntity) {
        usuarioEntity.setToken(tokenService.generateToken(usuarioEntity));
        UsuarioEntity usuarioCreated = this.usuarioService.create(usuarioEntity);
        return new ResponseEntity<UsuarioAuthenticationDTO>(UsuarioAuthenticationDTO.toDto(usuarioCreated, "Bearer "), HttpStatus.CREATED);
    }

}

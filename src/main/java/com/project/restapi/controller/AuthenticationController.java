package com.project.restapi.controller;

import com.project.restapi.model.entity.dto.UsuarioAuthenticationDTO;
import com.project.restapi.model.entity.dto.UsuarioDTO;
import com.project.restapi.model.entity.UsuarioEntity;
import com.project.restapi.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticateService authenticateService;

    public AuthenticationController(AuthenticateService authenticateService) {
        super();
        this.authenticateService = authenticateService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> authenticationUser(@RequestBody UsuarioDTO usuario) {
        UsuarioEntity usuarioConvertido = this.authenticateService.converter(usuario.getEmail());
        String token = this.authenticateService.gerarNovoToken(usuarioConvertido);
        usuarioConvertido.setToken(token);
        return new ResponseEntity<UsuarioAuthenticationDTO>(UsuarioAuthenticationDTO.toDto(usuarioConvertido, "Bearer "), HttpStatus.ACCEPTED);
    }

}

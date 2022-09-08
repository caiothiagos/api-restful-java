package com.project.restapi.model.entity.dto;

import com.project.restapi.model.entity.UsuarioEntity;

public class UsuarioAuthenticationDTO {

    private String tipo;
    private String email;
    private String nome;
    private String token;

    public UsuarioAuthenticationDTO() {
        super();
    }

    public UsuarioAuthenticationDTO(String tipo, String email, String nome, String token) {
        super();
        this.tipo = tipo;
        this.email = email;
        this.nome = nome;
        this.token = token;
    }

    public static UsuarioAuthenticationDTO toDto(UsuarioEntity usuarioEntity, String tipo) {
        return new UsuarioAuthenticationDTO(tipo, usuarioEntity.getEmail(), usuarioEntity.getNome(), usuarioEntity.getToken());
    }

    public String getTipo() {
        return tipo;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getToken() {
        return token;
    }

}

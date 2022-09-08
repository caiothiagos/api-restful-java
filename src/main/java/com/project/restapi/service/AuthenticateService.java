package com.project.restapi.service;

import com.project.restapi.exception.ExpirationException;
import com.project.restapi.exception.InvalidToken;
import com.project.restapi.exception.NegocioException;
import com.project.restapi.exception.ResourceNotFoundException;
import com.project.restapi.repository.UsuarioRepository;
import com.project.restapi.model.entity.UsuarioEntity;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.Date;

@Service
public class AuthenticateService {

    private UsuarioRepository usuarioRepository;
    private TokenService tokenService;

    @Autowired
    public AuthenticateService(UsuarioRepository usuarioRepository, TokenService tokenService) {
        super();
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }

    public UsuarioEntity authenticate(String email, String senha, String authorization) throws LoginException {
        UsuarioEntity usuarioEntity = this.usuarioRepository.findByEmailAddress(email);
        if(usuarioEntity != null){
            if(senha.equals(usuarioEntity.getSenha()) && !authorization.isEmpty() && validateToken(authorization)) {
                return usuarioEntity;
            }
        }
        throw new ResourceNotFoundException("Usuario não encontrado");
    }

    private boolean validateToken(String Authorization) {
        try {
            try {
                String tokenTratado = Authorization.replace("Bearer ", "");
                Claims claims = tokenService.decodeToken(tokenTratado);
                if(claims.getExpiration().before(new Date(System.currentTimeMillis()))) throw new ExpirationException();
                return true;
            }catch(ExpirationException ex) {
                ex.printStackTrace();
                throw ex;
            }catch(Exception e) {
                //e.printStackTrace();
                throw new InvalidToken();
            }
        } catch (ExpirationException e) {
            e.printStackTrace();
        }
        return false;
    }

    public UsuarioEntity converter(String email){
        UsuarioEntity usuario = this.usuarioRepository.findByEmailAddress(email);
        if(usuario != null){
            return usuario;
        }
        throw new ResourceNotFoundException("Usuário não encontrado.");
    }

    public Boolean validarTokenNaRequisicao(String token){
        if(!token.isEmpty()){
            Boolean tokenValido = validateToken(token);
            if(tokenValido){
                return true;
            }
        }
        throw new InvalidToken("Erro ao gerar token.");
    }


    public String gerarNovoToken(UsuarioEntity usuarioEntity){
        String token = this.tokenService.generateToken(usuarioEntity);
        if(!token.isEmpty()){
            return token;
        }
        throw new NegocioException("Erro ao gerar token.");
    }

}

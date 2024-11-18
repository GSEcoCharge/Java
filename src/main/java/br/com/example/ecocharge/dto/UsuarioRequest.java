package br.com.example.ecocharge.dto;

import java.time.LocalDateTime;

import br.com.example.ecocharge.model.Usuario;

public record UsuarioRequest(
    String nome,
    String email,
    String senha,
    String perfil,
    LocalDateTime criacao,
    String localizacao
) {
    public Usuario toModel() {
        return new Usuario();
    }

}

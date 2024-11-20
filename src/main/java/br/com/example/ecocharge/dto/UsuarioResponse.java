package br.com.example.ecocharge.dto;

import java.time.LocalDateTime;

import br.com.example.ecocharge.model.Usuario;

public record UsuarioResponse(
    Long id,
    String nome,
    String email,
    String perfil,
    LocalDateTime criacao,
    String localizacao
) {
    public static UsuarioResponse from(Usuario usuario) {
        return new UsuarioResponse(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getPerfil(),
            usuario.getCriacao(),
            usuario.getLocalizacao()
        );

    }
}

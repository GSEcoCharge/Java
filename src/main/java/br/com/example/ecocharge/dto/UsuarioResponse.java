package br.com.example.ecocharge.dto;

import br.com.example.ecocharge.model.Usuario;

public record UsuarioResponse(
    Long id,
    String nome,
    String email,
    String perfil,
    String localizacao
) {
    public static UsuarioResponse from(Usuario usuario) {
        return new UsuarioResponse(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getPerfil(),
            usuario.getLocalizacao()
        );

    }
}

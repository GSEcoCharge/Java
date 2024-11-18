package br.com.example.ecocharge.dto;

import java.time.LocalDate;

import br.com.example.ecocharge.model.Avaliacao;
import br.com.example.ecocharge.model.PostoCarregamento;

public record AvaliacaoResponse(
    Long id,
    UsuarioResponse usuario,
    PostoCarregamento posto,
    Integer nota,
    String comentario,
    LocalDate data

) {
    public static AvaliacaoResponse from(Avaliacao avaliacao){
        return new AvaliacaoResponse(
            avaliacao.getId(),
            UsuarioResponse.from(avaliacao.getUsuario()),
            avaliacao.getPosto(),
            avaliacao.getNota(),
            avaliacao.getComentario(),
            avaliacao.getData()
        );
    }

}

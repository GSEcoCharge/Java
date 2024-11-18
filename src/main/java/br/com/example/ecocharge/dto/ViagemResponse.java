package br.com.example.ecocharge.dto;

import java.time.LocalDate;

import br.com.example.ecocharge.model.Viagem;

public record ViagemResponse(
    Long id,
    UsuarioResponse usuario,
    String origem,
    String destino,
    int autonomiaRestante,
    LocalDate data
) {
    public static ViagemResponse from(Viagem viagem){
        return new ViagemResponse(
            viagem.getId(),
            UsuarioResponse.from(viagem.getUsuario()),
            viagem.getOrigem(),
            viagem.getDestino(),
            viagem.getAutonomiaRestante(),
            viagem.getData()
            );
    }
}

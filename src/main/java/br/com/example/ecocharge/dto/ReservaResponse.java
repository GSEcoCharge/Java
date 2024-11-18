package br.com.example.ecocharge.dto;

import java.time.LocalDate;

import br.com.example.ecocharge.model.PontoCarregamento;
import br.com.example.ecocharge.model.Reserva;

public record ReservaResponse(
    Long id,
    UsuarioResponse usuario,
    PontoCarregamento ponto,
    LocalDate data,
    String status
) {
    public static ReservaResponse from(Reserva reserva){
        return new ReservaResponse(
            reserva.getId(),
            UsuarioResponse.from(reserva.getUsuario()),
            reserva.getPonto(),
            reserva.getData(),
            reserva.getStatus()
        );
    }

}

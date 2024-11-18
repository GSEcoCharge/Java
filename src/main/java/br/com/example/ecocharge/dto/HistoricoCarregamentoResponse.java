package br.com.example.ecocharge.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.example.ecocharge.model.HistoricoCarregamento;
import br.com.example.ecocharge.model.PontoCarregamento;

public record HistoricoCarregamentoResponse(
    Long id,
    UsuarioResponse usuario,
    PontoCarregamento ponto,
    LocalDate data,
    BigDecimal consumo,
    BigDecimal emissoes

) {
    public static HistoricoCarregamentoResponse from(HistoricoCarregamento historicoCarregamento){
        return new HistoricoCarregamentoResponse(
            historicoCarregamento.getId(),
            UsuarioResponse.from(historicoCarregamento.getUsuario()),
            historicoCarregamento.getPonto(),
            historicoCarregamento.getData(),
            historicoCarregamento.getConsumo(),
            historicoCarregamento.getEmissoes()
        );
    }

}

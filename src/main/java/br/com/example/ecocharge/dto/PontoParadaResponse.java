package br.com.example.ecocharge.dto;

import br.com.example.ecocharge.model.PontoCarregamento;
import br.com.example.ecocharge.model.PontoParada;

public record PontoParadaResponse(
    Long id,
    ViagemResponse viagem,
    PontoCarregamento ponto,
    Integer ordem
) {
    public static PontoParadaResponse from(PontoParada pontoParada){
        return new PontoParadaResponse(
            pontoParada.getId(),
            ViagemResponse.from(pontoParada.getViagem()),
            pontoParada.getPonto(),
            pontoParada.getOrdem()
        );
    }

}

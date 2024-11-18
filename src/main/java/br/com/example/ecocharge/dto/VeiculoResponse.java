package br.com.example.ecocharge.dto;

import br.com.example.ecocharge.model.Veiculo;

public record VeiculoResponse(
    Long id,
    UsuarioResponse usuario,
    String marca,
    String modelo,
    Integer ano,
    Integer autonomia,
    String conector
    
) {
    public static VeiculoResponse from(Veiculo veiculo) {
        return new VeiculoResponse(
            veiculo.getId(),
            UsuarioResponse.from(veiculo.getUsuario()),
            veiculo.getMarca(),
            veiculo.getModelo(),
            veiculo.getAno(),
            veiculo.getAutonomia(),
            veiculo.getConector()
        );
            
    }

}

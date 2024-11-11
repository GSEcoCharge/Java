package br.com.example.ecocharge.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ponto_parada")
public class PontoParada {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ponto_parada_id;

    @OneToOne
    @JoinColumn(name = "viagem_id")
    private Viagem viagem_id;

    @OneToOne
    @JoinColumn(name = "ponto_id")
    private PontoCarregamento ponto_id;

    private int ordem;

}

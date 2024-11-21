package br.com.example.ecocharge.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GS_PONTO_PARADA")
public class PontoParada {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pontoParada")
    @SequenceGenerator(name = "pontoParada", sequenceName = "GS_PONTO_PARADA_SEQ", allocationSize = 1)
    @Column(name = "PONTO_PARADA_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "VIAGEM_ID")
    private Viagem viagem;

    @OneToOne
    @JoinColumn(name = "PONTO_ID")
    private PontoCarregamento ponto;

    @NotNull
    @Min(1)
    @Column(name = "ORDEM")
    private Integer ordem;

}

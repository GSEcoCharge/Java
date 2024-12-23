package br.com.example.ecocharge.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
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
@Table(name = "GS_HISTORICO_CARREGAMENTO")
public class HistoricoCarregamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historico")
    @SequenceGenerator(name = "historico", sequenceName = "GS_HISTORICO_CARREGAMENTO_SEQ", allocationSize = 1)
    @Column(name = "HISTORICO_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "PONTO_ID")
    private PontoCarregamento ponto;

    @Column(name = "DATA_CARREGAMENTO")
    private LocalDate data;

    @NotNull
    @DecimalMin("0.0")
    @Column(name = "ENERGIA_CONSUMIDA")
    private BigDecimal consumo;

    @NotNull
    @DecimalMin("0.0")
    @Column(name = "EMISSOES_EVITADAS")
    private BigDecimal emissoes;

}

package br.com.example.ecocharge.model;

import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "historico_carregamento")
public class HistoricoCarregamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long historico_id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario_id;

    @OneToOne
    @JoinColumn(name = "ponto_id")
    private PontoCarregamento ponto_id;

    private Date data_carregamento;

    private BigDecimal energia_consumida;

    private BigDecimal emissoes_evitadas;

}

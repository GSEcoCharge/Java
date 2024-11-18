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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GS_PONTO_CARREGAMENTO")
public class PontoCarregamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pontoCarregamento")
    @SequenceGenerator(name = "pontoCarregamento", sequenceName = "GS_PONTO_CARREGAMENTO_SEQ", allocationSize = 1)
    @Column(name = "ponto_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "POSTO_ID")
    private PostoCarregamento posto;

    @Column(name = "TIPO_CONECTOR")
    private String conector;

    @Column(name = "VELOCIDADE_CARREGAMENTO")
    private Integer carregamento;

    @Column(name = "DISPONIBILIDADE")
    private String disponibilidade;

    @Column(name = "RESERVAVEL")
    private Character reservavel;

}

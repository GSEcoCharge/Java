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
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    @Column(name = "TIPO_CONECTOR")
    private String conector;

    @NotNull
    @Min(0)
    @Column(name = "VELOCIDADE_CARREGAMENTO")
    private Integer carregamento;

    @NotBlank
    @Column(name = "DISPONIBILIDADE")
    private String disponibilidade;

    @NotNull
    @Column(name = "RESERVAVEL")
    private Character reservavel;

}

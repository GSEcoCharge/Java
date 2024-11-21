package br.com.example.ecocharge.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
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
@Table(name = "GS_POSTO_CARREGAMENTO")
public class PostoCarregamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postoParada")
    @SequenceGenerator(name = "postoParada", sequenceName = "GS_POSTO_CARREGAMENTO_SEQ", allocationSize = 1)
    @Column(name = "POSTO_ID")
    private Long id;

    @NotBlank
    @Column(name = "NOME")
    private String nome;

    @NotNull
    @Column(name = "LATITUDE")
    private BigDecimal latitude;

    @NotNull
    @Column(name = "LONGITUDE")
    private BigDecimal longitude;

    @NotBlank
    @Column(name = "ENDERECO")
    private String endereco;

    @NotBlank
    @Column(name = "HORARIO_FUNCIONAMENTO")
    private String expediente;

    @NotBlank
    @Column(name = "FORMAS_PAGAMENTO")
    private String pagamento;

    @DecimalMin("0.0")
    @Column(name = "AVALIACAO_MEDIA")
    private BigDecimal avaliacao;

}

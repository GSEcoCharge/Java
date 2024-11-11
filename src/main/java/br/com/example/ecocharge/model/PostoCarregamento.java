package br.com.example.ecocharge.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "GS_POSTO_CARREGAMENTO")
public class PostoCarregamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postoParada")
    @SequenceGenerator(name = "postoParada", sequenceName = "GS_POSTO_CARREGAMENTO_SEQ", allocationSize = 1)
    @Column(name = "POSTO_ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "LATITUDE")
    private BigDecimal latitude;

    @Column(name = "LONGITUDE")
    private BigDecimal longitude;

    @Column(name = "ENDERECO")
    private String endereco;

    @Column(name = "HORARIO_FUNCIONAMENTO")
    private String expediente;

    @Column(name = "FORMAS_PAGAMENTO")
    private String pagamento;

    @Column(name = "AVALIACAO_MEDIA")
    private BigDecimal avaliacao;

}

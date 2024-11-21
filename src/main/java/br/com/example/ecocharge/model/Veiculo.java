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
@Table(name = "GS_VEICULO")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veiculo")
    @SequenceGenerator(name = "veiculo", sequenceName = "GS_VEICULO_SEQ", allocationSize = 1)
    @Column(name = "VEICULO_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    @NotBlank
    @Column(name = "MARCA")
    private String marca;

    @NotBlank
    @Column(name = "MODELO")
    private String modelo;

    @NotNull
    @Min(1886)
    @Column(name = "ANO")
    private Integer ano;

    @NotNull
    @Min(0)
    @Column(name = "AUTONOMIA")
    private Integer autonomia;

    @NotBlank
    @Column(name = "TIPO_CONECTOR")
    private String conector;

}
package br.com.example.ecocharge.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GS_VIAGEM")
public class Viagem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "viagem")
    @SequenceGenerator(name = "viagem", sequenceName = "GS_VIAGEM_SEQ", allocationSize = 1)
    @Column(name = "VIAGEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    @NotBlank
    @Column(name = "ORIGEM")
    private String origem;

    @NotBlank
    @Column(name = "DESTINO")
    private String destino;
    
    @NotNull
    @Min(0)
    @Column(name = "AUTONOMIA_RESTANTE")
    private Integer autonomiaRestante;

    @NotNull
    @Column(name = "DATA_CRIACAO")
    private LocalDate data;

}

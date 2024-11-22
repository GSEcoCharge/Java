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
@Table(name = "GS_AVALIACAO")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "avaliacao")
    @SequenceGenerator(name = "avaliacao", sequenceName = "GS_AVALIACAO_SEQ", allocationSize = 1)
    @Column(name = "AVALIACAO_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "POSTO_ID")
    private PostoCarregamento posto;
    
    @NotNull
    @Min(1)
    @Max(5)
    @Column(name = "NOTA")
    private Integer nota;

    @NotBlank
    @Column(name = "COMENTARIO")
    private String comentario;

    @Column(name = "DATA_AVALIACAO")
    private LocalDate data;

}

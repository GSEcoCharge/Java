package br.com.example.ecocharge.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    
    @Column(name = "NOTA")
    private int nota;

    @Column(name = "COMENTARIO")
    private String comentario;

    @Column(name = "DATA_AVALIACAO")
    private LocalDate data;

}

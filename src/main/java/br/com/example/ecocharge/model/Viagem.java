package br.com.example.ecocharge.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Viagem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long viagem_id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario_id;

    private String origem;

    private String destino;

    private int autonomia_restante;

    private Date data_avaliacao;

}

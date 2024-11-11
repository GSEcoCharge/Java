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
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long avaliacao_id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario_id;

    @ManyToOne
    @JoinColumn(name = "posto_id")
    private PostoCarregamento posto_id;
    
    private int nota;

    private String comentario;

    private Date data_avaliacao;

}

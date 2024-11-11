package br.com.example.ecocharge.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "ponto_carregamento")
public class PontoCarregamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ponto_id;

    @OneToOne
    @JoinColumn(name = "posto_id")
    private PostoCarregamento posto_id;

    private String tipoConector;

    private int velocidade_carregamento;

    private String disponibilidade;

    private char reservavel;

}

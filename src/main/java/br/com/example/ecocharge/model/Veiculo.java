package br.com.example.ecocharge.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long veiculo_id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario_id;

    private String marca;

    private String modelo;

    private int ano;

    private int autonomia;

    private String tipoConector;

    
}
package br.com.example.ecocharge.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reserva_id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario_id;

    @OneToOne
    @JoinColumn(name = "ponto_id")
    private PontoCarregamento ponto_id;

    private Date data_reserva;

    private String status;

}

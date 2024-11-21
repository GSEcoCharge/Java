package br.com.example.ecocharge.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Table(name = "GS_RESERVA")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserva")
    @SequenceGenerator(name = "reserva", sequenceName = "GS_RESERVA_SEQ", allocationSize = 1)
    @Column(name = "RESERVA_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "PONTO_ID")
    private PontoCarregamento ponto;

    @NotNull
    @Column(name = "DATA_RESERVA")
    private LocalDate data;

    @NotBlank
    @Column(name = "STATUS")
    private String status;

}

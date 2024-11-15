package br.com.example.ecocharge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.example.ecocharge.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT r FROM Reserva r WHERE r.usuario = :id")
    List<Reserva> findAllByUsuarioId(Long id);

    @Query("SELECT r FROM Reserva r WHERE r.ponto = :id")
    List<Reserva> findAllByPontoId(Long id);

}

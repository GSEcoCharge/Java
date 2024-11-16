package br.com.example.ecocharge.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.example.ecocharge.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT r FROM Reserva r WHERE r.usuario = :id")
    List<Reserva> findAllByUsuarioId(Long id);

    @Query("SELECT r FROM Reserva r WHERE r.ponto = :id")
    List<Reserva> findAllByPontoId(Long id);

    @Query("SELECT r FROM Reserva r WHERE r.data = :data")
    List<Reserva> findAllByData(String data);

    @Query("SELECT r FROM Reserva r WHERE r.status = :status")
    List<Reserva> findAllByStatus(String status);

    @Query("SELECT r FROM Reserva r WHERE r.status = :status AND r.data = :data")
    Page<Reserva> findAllByStatusAndData(String status, String data, Pageable pageable);

    @Query("SELECT r FROM Reserva r WHERE r.status = :status")
    Page<Reserva> findAllByStatus(String status, Pageable pageable);

    @Query("SELECT r FROM Reserva r WHERE r.data = :data")
    Page<Reserva> findAllByData(String data, Pageable pageable);
}

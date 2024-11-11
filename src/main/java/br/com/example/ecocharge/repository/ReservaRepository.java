package br.com.example.ecocharge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.example.ecocharge.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}

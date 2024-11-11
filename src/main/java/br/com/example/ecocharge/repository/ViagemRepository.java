package br.com.example.ecocharge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.ecocharge.model.Viagem;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {

}

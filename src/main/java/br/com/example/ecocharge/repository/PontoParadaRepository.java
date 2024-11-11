package br.com.example.ecocharge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.ecocharge.model.PontoParada;

public interface PontoParadaRepository extends JpaRepository<PontoParada, Long> {
    
}

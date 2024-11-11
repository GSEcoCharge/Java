package br.com.example.ecocharge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.ecocharge.model.HistoricoCarregamento;

public interface HistoricoCarregamentoRepository extends JpaRepository<HistoricoCarregamento, Long> {

}

package br.com.example.ecocharge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.example.ecocharge.model.HistoricoCarregamento;

public interface HistoricoCarregamentoRepository extends JpaRepository<HistoricoCarregamento, Long> {
    @Query("SELECT h FROM HistoricoCarregamento h WHERE h.usuario = :id")
    List<HistoricoCarregamento> findAllByUsuarioId(Long id);

    @Query("SELECT h FROM HistoricoCarregamento h WHERE h.ponto = :id")
    List<HistoricoCarregamento> findAllByPontoId(Long id);

}

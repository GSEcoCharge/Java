package br.com.example.ecocharge.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.example.ecocharge.model.HistoricoCarregamento;

public interface HistoricoCarregamentoRepository extends JpaRepository<HistoricoCarregamento, Long> {
    @Query("SELECT h FROM HistoricoCarregamento h WHERE h.usuario = :id")
    List<HistoricoCarregamento> findAllByUsuarioId(Long id);

    @Query("SELECT h FROM HistoricoCarregamento h WHERE h.ponto = :id")
    List<HistoricoCarregamento> findAllByPontoId(Long id);

    @Query("SELECT h FROM HistoricoCarregamento h WHERE h.data = :data")
    List<HistoricoCarregamento> findAllByData(String data);

    @Query("SELECT h FROM HistoricoCarregamento h WHERE h.consumo = :consumo")
    List<HistoricoCarregamento> findAllByConsumo(int consumo);

    @Query("SELECT h FROM HistoricoCarregamento h WHERE h.emissoes = :emissoes")
    List<HistoricoCarregamento> findAllByEmissoes(int emissoes);

    @Query("SELECT h FROM HistoricoCarregamento h WHERE h.data = :data AND h.consumo = :consumo AND h.emissoes = :emissoes")
    Page<HistoricoCarregamento> findAllByDataAndConsumoAndEmissoes(LocalDate data, BigDecimal consumo,
            BigDecimal emissoes, Pageable pageable);

    @Query("SELECT h FROM HistoricoCarregamento h WHERE h.consumo = :consumo AND h.emissoes = :emissoes")
    Page<HistoricoCarregamento> findAllByConsumoAndEmissoes(BigDecimal consumo, BigDecimal emissoes, Pageable pageable);

    @Query("SELECT h FROM HistoricoCarregamento h WHERE h.emissoes = :emissoes AND h.data = :data")
    Page<HistoricoCarregamento> findAllByEmissoesAndData(BigDecimal emissoes, LocalDate data, Pageable pageable);

    @Query("SELECT h FROM HistoricoCarregamento h WHERE h.data = :data AND h.consumo = :consumo")
    Page<HistoricoCarregamento> findAllByDataAndConsumo(LocalDate data, BigDecimal consumo, Pageable pageable);

}

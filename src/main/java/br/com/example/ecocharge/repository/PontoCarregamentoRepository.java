package br.com.example.ecocharge.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.example.ecocharge.model.PontoCarregamento;

public interface PontoCarregamentoRepository extends JpaRepository<PontoCarregamento, Long> {

    @Query("SELECT p FROM PontoCarregamento p WHERE p.disponibilidade = :disponibilidade")
    Page<PontoCarregamento> findAllByDisponibilidade(@Param("disponibilidade") String disponibilidade, Pageable pageable);

    @Query("SELECT p FROM PontoCarregamento p WHERE p.conector = :tipoConector")
    Page<PontoCarregamento> findAllByTipoConector(@Param("tipoConector") String tipoConector, Pageable pageable);

    @Query("SELECT p FROM PontoCarregamento p WHERE p.carregamento = :velocidadeCarregamento")
    Page<PontoCarregamento> findAllByVelocidadeCarregamento(@Param("velocidadeCarregamento") Integer velocidadeCarregamento, Pageable pageable);

    @Query("SELECT p FROM PontoCarregamento p WHERE p.reservavel = :reservavel")
    Page<PontoCarregamento> findAllByReservavel(@Param("reservavel") char reservavel, Pageable pageable);

    @Query("SELECT p FROM PontoCarregamento p WHERE p.conector = :tipoConector AND p.carregamento = :velocidadeCarregamento AND p.reservavel = :reservavel")
    Page<PontoCarregamento> findAllByTipoConectorAndPotenciaAndReservavel(String tipoConector, Integer velocidadeCarregamento,
            Character reservavel, Pageable pageable);

    @Query("SELECT p FROM PontoCarregamento p WHERE p.conector = :tipoConector AND p.reservavel = :reservavel")
    Page<PontoCarregamento> findAllByTipoConectorPotenciaAndReservavel(String tipoConector, Character reservavel,
            Pageable pageable);
    
}
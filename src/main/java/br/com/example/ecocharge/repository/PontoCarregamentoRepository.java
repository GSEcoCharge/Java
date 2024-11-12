package br.com.example.ecocharge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.example.ecocharge.model.PontoCarregamento;

public interface PontoCarregamentoRepository extends JpaRepository<PontoCarregamento, Long> {

    @Query("SELECT p FROM PontoCarregamento p WHERE p.disponibilidade = :disponibilidade")
    List<PontoCarregamento> findAllByDisponibilidade(String disponibilidade);

    @Query("SELECT p FROM PontoCarregamento p WHERE p.conector = :tipoConector")
    List<PontoCarregamento> findAllByTipoConector(String tipoConector);

    @Query("SELECT p FROM PontoCarregamento p WHERE p.carregamento = :velocidadeCarregamento")
    List<PontoCarregamento> findAllByVelocidadeCarregamento(Integer velocidadeCarregamento);

    @Query("SELECT p FROM PontoCarregamento p WHERE p.reservavel = :reservavel")
    List<PontoCarregamento> findAllByReservavel(char reservavel);
    
}
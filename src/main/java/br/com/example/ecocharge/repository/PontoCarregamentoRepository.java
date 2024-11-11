package br.com.example.ecocharge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.example.ecocharge.model.PontoCarregamento;

public interface PontoCarregamentoRepository extends JpaRepository<PontoCarregamento, Long> {

    @Query("SELECT p FROM PontoCarregamento p WHERE p.disponibilidade = :disponibilidade")
    PontoCarregamento findByDisponibilidade(String disponibilidade);

    @Query("SELECT p FROM PontoCarregamento p WHERE p.conector = :tipoConector")
    PontoCarregamento findByTipoConector(String tipoConector);

    @Query("SELECT p FROM PontoCarregamento p WHERE p.carregamento = :velocidadeCarregamento")
    PontoCarregamento findByVelocidadeCarregamento(Integer velocidadeCarregamento);

    @Query("SELECT p FROM PontoCarregamento p WHERE p.reservavel = :reservavel")
    PontoCarregamento findByReservavel(char reservavel);
    
}
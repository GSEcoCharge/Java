package br.com.example.ecocharge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.example.ecocharge.model.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("SELECT a FROM Avaliacao a WHERE a.usuario = :id")
    List<Avaliacao> findAllByUsuarioId(Long id);

    @Query("SELECT a FROM Avaliacao a WHERE a.posto = :id")
    List<Avaliacao> findAllByPostoId(Long id);

}

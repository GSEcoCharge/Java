package br.com.example.ecocharge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.example.ecocharge.model.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("SELECT a FROM Avaliacao a WHERE a.usuario_id = :id")
    Avaliacao findByUsuarioId(Long id);

    @Query("SELECT a FROM Avaliacao a WHERE a.posto_id = :id")
    Avaliacao findByPostoId(Long id);

}

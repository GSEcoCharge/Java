package br.com.example.ecocharge.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.example.ecocharge.model.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("SELECT a FROM Avaliacao a WHERE a.usuario = :id")
    List<Avaliacao> findAllByUsuarioId(Long id);

    @Query("SELECT a FROM Avaliacao a WHERE a.posto = :id")
    List<Avaliacao> findAllByPostoId(Long id);

    @Query("SELECT a FROM Avaliacao a WHERE a.nota = :nota AND a.data = :data")
    Page<Avaliacao> findAllByNotaAndData(Integer nota, LocalDate data, Pageable pageable);

    @Query("SELECT a FROM Avaliacao a WHERE a.data = :data")
    Page<Avaliacao> findAllByData(LocalDate data, Pageable pageable);

    @Query("SELECT a FROM Avaliacao a WHERE a.nota = :nota")
    Page<Avaliacao> findAllByNota(Integer nota, Pageable pageable);

}

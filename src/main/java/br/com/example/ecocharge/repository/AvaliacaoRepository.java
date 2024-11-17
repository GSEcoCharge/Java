package br.com.example.ecocharge.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.example.ecocharge.model.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    @Query("SELECT a FROM Avaliacao a WHERE a.usuario.id = :id")
    Page<Avaliacao> findAllByUsuarioId(Long id, Pageable pageable);

    @Query("SELECT a FROM Avaliacao a WHERE a.posto.id = :id")
    Page<Avaliacao> findAllByPostoId(Long id, Pageable pageable);

    @Query("SELECT a FROM Avaliacao a WHERE a.nota = :nota AND a.data = :data")
    Page<Avaliacao> findAllByNotaAndData(Integer nota, LocalDate data, Pageable pageable);

    @Query("SELECT a FROM Avaliacao a WHERE a.data = :data")
    Page<Avaliacao> findAllByData(LocalDate data, Pageable pageable);

    @Query("SELECT a FROM Avaliacao a WHERE a.nota = :nota")
    Page<Avaliacao> findAllByNota(Integer nota, Pageable pageable);

    @Query("SELECT a FROM Avaliacao a WHERE a.usuario.id = :id AND a.nota = :nota AND a.data = :data")
    Page<Avaliacao> findAllByIdUsuarioWithNotaAndData(Long id, Integer nota, LocalDate data, Pageable pageable);

    @Query("SELECT a FROM Avaliacao a WHERE a.usuario.id = :id AND a.nota = :nota")
    Page<Avaliacao> findAllByIdUsuarioWithNota(Long id, Integer nota, Pageable pageable);

    @Query("SELECT a FROM Avaliacao a WHERE a.usuario.id = :id AND a.data = :data")
    Page<Avaliacao> findAllByIdUsuarioWithData(Long id, LocalDate data, Pageable pageable);

    @Query("SELECT a FROM Avaliacao a WHERE a.posto.id = :id AND a.nota = :nota AND a.data = :data")
    Page<Avaliacao> findAllByIdPostoWithNotaAndData(Long id, Integer nota, LocalDate data, Pageable pageable);

    @Query("SELECT a FROM Avaliacao a WHERE a.posto.id = :id AND a.nota = :nota")
    Page<Avaliacao> findAllByIdPostoWithNota(Long id, Integer nota, Pageable pageable);

    @Query("SELECT a FROM Avaliacao a WHERE a.posto.id = :id AND a.data = :data")
    Page<Avaliacao> findAllByIdPostoWithData(Long id, LocalDate data, Pageable pageable);

}

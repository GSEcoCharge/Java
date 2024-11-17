package br.com.example.ecocharge.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.example.ecocharge.model.Avaliacao;
import br.com.example.ecocharge.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Page<Avaliacao> findAll(Pageable pageable) {
        return avaliacaoRepository.findAll(pageable);
    }

    public Page<Avaliacao> findAllByNotaAndData(Integer nota, LocalDate data, Pageable pageable) {
        return avaliacaoRepository.findAllByNotaAndData(nota, data, pageable);
    }

    public Page<Avaliacao> findAllByNota(Integer nota, Pageable pageable) {
        return avaliacaoRepository.findAllByNota(nota, pageable);
    }

    public Page<Avaliacao> findAllByData(LocalDate data, Pageable pageable) {
        return avaliacaoRepository.findAllByData(data, pageable);
    }

    public Page<Avaliacao> findAllByUsuarioId(Long id,Pageable pageable) {
        return avaliacaoRepository.findAllByUsuarioId(id, pageable);
    }

    public Page<Avaliacao> findAllByPostoId(Long id, Pageable pageable) {
        return avaliacaoRepository.findAllByPostoId(id, pageable);
    }
    public Page<Avaliacao> findAllByIdUsuarioWithNotaAndData(Long id, Integer nota, LocalDate data, Pageable pageable) {
        return avaliacaoRepository.findAllByIdUsuarioWithNotaAndData(id, nota, data, pageable);
    }

    public Page<Avaliacao> findAllByIdUsuarioWithNota(Long id, Integer nota, Pageable pageable) {
        return avaliacaoRepository.findAllByIdUsuarioWithNota(id, nota, pageable);
    }

    public Page<Avaliacao> findAllByIdUsuarioWithData(Long id, LocalDate data, Pageable pageable) {
        return avaliacaoRepository.findAllByIdUsuarioWithData(id, data, pageable);
    }

    public Page<Avaliacao> findAllByIdPostoWithNotaAndData(Long id, Integer nota, LocalDate data, Pageable pageable) {
        return avaliacaoRepository.findAllByIdUsuarioWithNotaAndData(id, nota, data, pageable);
    }

    public Page<Avaliacao> findAllByIdPostoWithNota(Long id, Integer nota, Pageable pageable) {
        return avaliacaoRepository.findAllByIdUsuarioWithNota(id, nota, pageable);
    }

    public Page<Avaliacao> findAllByIdPostoWithData(Long id, LocalDate data, Pageable pageable) {
        return avaliacaoRepository.findAllByIdUsuarioWithData(id, data, pageable);
    }
    public Avaliacao findById(Long id) {
        return avaliacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrada a avaliação com o id: " + id));
    }

    public Avaliacao create(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    public void deleteById(Long id) {
        avaliacaoRepository.deleteById(id);
    }

    public Avaliacao update(Long id, Avaliacao avaliacao) {
        verificarId(id);
        avaliacao.setId(id);
        return avaliacaoRepository.save(avaliacao);
    }

    public void verificarId(Long id){
        avaliacaoRepository.
        findById(id)
        .orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "id não encontrado")
        );
    }
}

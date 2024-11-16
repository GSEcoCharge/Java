package br.com.example.ecocharge.service;

import java.time.LocalDate;
import java.util.List;
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

    public List<Avaliacao> findAllByUsuarioId(Long id) {
        return avaliacaoRepository.findAllByUsuarioId(id);
    }

    public List<Avaliacao> findAllByPostoId(Long id) {
        return avaliacaoRepository.findAllByPostoId(id);
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

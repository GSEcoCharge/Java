package br.com.example.ecocharge.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.example.ecocharge.model.Avaliacao;
import br.com.example.ecocharge.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public List<Avaliacao> findAll() {
        return avaliacaoRepository.findAll();
    }

    public Avaliacao findById(Long id) {
        return avaliacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrada a avaliação com o id: " + id));
    }

    public Avaliacao findByUsuarioId(Long id) {
        return avaliacaoRepository.findByUsuarioId(id);
    }

    public Avaliacao findByPostoId(Long id) {
        return avaliacaoRepository.findByPostoId(id);
    }

    public Avaliacao create(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    public void deleteById(Long id) {
        avaliacaoRepository.deleteById(id);
    }
}

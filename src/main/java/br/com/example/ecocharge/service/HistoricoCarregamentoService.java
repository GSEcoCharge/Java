package br.com.example.ecocharge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import br.com.example.ecocharge.model.HistoricoCarregamento;
import br.com.example.ecocharge.repository.HistoricoCarregamentoRepository;

@Service
public class HistoricoCarregamentoService {

    @Autowired
    private HistoricoCarregamentoRepository historicoCarregamentoRepository;

    public List<HistoricoCarregamento> findAll() {
        return historicoCarregamentoRepository.findAll();
    }

    public HistoricoCarregamento findById(Long id) {
        return historicoCarregamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado o histórico de carregamento com o id: " + id));
    }

    public HistoricoCarregamento create(HistoricoCarregamento historicoCarregamento) {
        return historicoCarregamentoRepository.save(historicoCarregamento);
    }

    public void deleteById(Long id) {
        historicoCarregamentoRepository.deleteById(id);
    }

    public HistoricoCarregamento update(Long id, HistoricoCarregamento historicoCarregamento) {
        verificarId(id);
        historicoCarregamento.setId(id);
        return historicoCarregamentoRepository.save(historicoCarregamento);
    }

    public void verificarId(Long id){
        historicoCarregamentoRepository.
        findById(id)
        .orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "id não encontrado")
        );
    }
}

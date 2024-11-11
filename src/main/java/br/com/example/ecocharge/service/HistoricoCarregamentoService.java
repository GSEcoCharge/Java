package br.com.example.ecocharge.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}

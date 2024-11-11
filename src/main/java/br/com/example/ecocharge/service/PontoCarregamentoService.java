package br.com.example.ecocharge.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.example.ecocharge.model.PontoCarregamento;
import br.com.example.ecocharge.repository.PontoCarregamentoRepository;

@Service
public class PontoCarregamentoService {

    @Autowired
    private PontoCarregamentoRepository pontoCarregamentoRepository;

    public List<PontoCarregamento> findAll() {
        return pontoCarregamentoRepository.findAll();
    }

    public PontoCarregamento findById(Long id) {
        return pontoCarregamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado o ponto de carregamento com o id: " + id));
    }

    public PontoCarregamento findByTipoConector(String tipoConector) {
        return pontoCarregamentoRepository.findByTipoConector(tipoConector);
    }

    public PontoCarregamento findByVelocidadeCarregamento(int velocidadeCarregamento) {
        return pontoCarregamentoRepository.findByVelocidadeCarregamento(velocidadeCarregamento);
    }
    
    public PontoCarregamento findByDisponibilidade(String disponibilidade) {
        return pontoCarregamentoRepository.findByDisponibilidade(disponibilidade);
    }

    public PontoCarregamento findByReservavel(char reservavel) {
        return pontoCarregamentoRepository.findByReservavel(reservavel);
    }

    public PontoCarregamento create(PontoCarregamento pontoCarregamento) {
        return pontoCarregamentoRepository.save(pontoCarregamento);
    }

    public void deleteById(Long id) {
        pontoCarregamentoRepository.deleteById(id);
    }
}

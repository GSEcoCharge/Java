package br.com.example.ecocharge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public List<PontoCarregamento> findAllByTipoConector(String tipoConector) {
        return pontoCarregamentoRepository.findAllByTipoConector(tipoConector);
    }

    public List<PontoCarregamento> findAllByVelocidadeCarregamento(int velocidadeCarregamento) {
        return pontoCarregamentoRepository.findAllByVelocidadeCarregamento(velocidadeCarregamento);
    }
    
    public List<PontoCarregamento> findAllByDisponibilidade(String disponibilidade) {
        return pontoCarregamentoRepository.findAllByDisponibilidade(disponibilidade);
    }

    public List<PontoCarregamento> findAllByReservavel(char reservavel) {
        return pontoCarregamentoRepository.findAllByReservavel(reservavel);
    }

    public PontoCarregamento create(PontoCarregamento pontoCarregamento) {
        return pontoCarregamentoRepository.save(pontoCarregamento);
    }

    public void deleteById(Long id) {
        pontoCarregamentoRepository.deleteById(id);
    }

    public PontoCarregamento update(Long id, PontoCarregamento pontoCarregamento) {
        verificarId(id);
        pontoCarregamento.setId(id);
        return pontoCarregamentoRepository.save(pontoCarregamento);
    }

    public void verificarId(Long id){
        pontoCarregamentoRepository.
        findById(id)
        .orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "id não encontrado")
        );
    }
}

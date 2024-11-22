package br.com.example.ecocharge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<PontoCarregamento> findAll(Pageable pageable) {
        return pontoCarregamentoRepository.findAll(pageable);
    }

    public PontoCarregamento findById(Long id) {
        return pontoCarregamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi encontrado o ponto de carregamento com o id: " + id));
    }

    public Page<PontoCarregamento> findAllByPotenciaAndTipoConectorAndReservavelAndDisponivel(
            Integer velocidadeCarregamento, String tipoConector, Character reservavel, String disponibilidade,
            Pageable pageable) {
        return pontoCarregamentoRepository.findAllByPotenciaAndTipoConectorAndReservavelAndDisponivel(
                velocidadeCarregamento, tipoConector, reservavel, disponibilidade, pageable);
    }

    public Page<PontoCarregamento> findAllByPotenciaAndReservavelAndDisponivel(Integer velocidadeCarregamento,
            Character reservavel, String disponibilidade, Pageable pageable) {
        return pontoCarregamentoRepository.findAllByPotenciaAndReservavelAndDisponivel(velocidadeCarregamento,
                reservavel, disponibilidade, pageable);
    }

    public Page<PontoCarregamento> findAllByTipoConectorAndReservavelAndDisponivel(String tipoConector,
            Character reservavel, String disponibilidade, Pageable pageable) {
        return pontoCarregamentoRepository.findAllByTipoConectorAndReservavelAndDisponivel(tipoConector, reservavel,
                disponibilidade, pageable);
    }

    public Page<PontoCarregamento> findAllByTipoConectorAndPotencia(String tipoConector, Integer velocidadeCarregamento,
            Pageable pageable) {
        return pontoCarregamentoRepository.findAllByTipoConectorAndPotencia(tipoConector, velocidadeCarregamento,
                pageable);
    }

    public Page<PontoCarregamento> findAllByDisponivelAndReservavel(String disponibilidade, Character reservavel,
            Pageable pageable) {
        return pontoCarregamentoRepository.findAllByDisponivelAndReservavel(disponibilidade, reservavel, pageable);
    }

    public Page<PontoCarregamento> findAllByTipoConectorAndReservavel(String tipoConector, Character reservavel,
            Pageable pageable) {
        return pontoCarregamentoRepository.findAllByTipoConectorPotenciaAndReservavel(tipoConector, reservavel,
                pageable);
    }

    public Page<PontoCarregamento> findAllByTipoConector(String tipoConector, Pageable pageable) {
        return pontoCarregamentoRepository.findAllByTipoConector(tipoConector, pageable);
    }

    public Page<PontoCarregamento> findAllByVelocidadeCarregamento(int velocidadeCarregamento, Pageable pageable) {
        return pontoCarregamentoRepository.findAllByVelocidadeCarregamento(velocidadeCarregamento, pageable);
    }

    public Page<PontoCarregamento> findAllByDisponibilidade(String disponibilidade, Pageable pageable) {
        return pontoCarregamentoRepository.findAllByDisponibilidade(disponibilidade, pageable);
    }

    public Page<PontoCarregamento> findAllByReservavel(char reservavel, Pageable pageable) {
        return pontoCarregamentoRepository.findAllByReservavel(reservavel, pageable);
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

    public void verificarId(Long id) {
        pontoCarregamentoRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id não encontrado"));
    }
}

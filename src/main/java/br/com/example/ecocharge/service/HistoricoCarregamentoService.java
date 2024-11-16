package br.com.example.ecocharge.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.example.ecocharge.model.HistoricoCarregamento;
import br.com.example.ecocharge.repository.HistoricoCarregamentoRepository;

@Service
public class HistoricoCarregamentoService {

    @Autowired
    private HistoricoCarregamentoRepository historicoCarregamentoRepository;

    public Page<HistoricoCarregamento> findAll(Pageable pageable) {
        return historicoCarregamentoRepository.findAll(pageable);
    }

    public Page<HistoricoCarregamento> findAllByDataAndConsumoAndEmissoes(LocalDate data, BigDecimal consumo,
            BigDecimal emissoes, Pageable pageable) {
                return historicoCarregamentoRepository.findAllByDataAndConsumoAndEmissoes(data, consumo, emissoes, pageable);
    }

    public Page<HistoricoCarregamento> findAllByConsumoAndEmissoes(BigDecimal consumo, BigDecimal emissoes,
            Pageable pageable) {
                return historicoCarregamentoRepository.findAllByConsumoAndEmissoes(consumo, emissoes, pageable);
    }

    public Page<HistoricoCarregamento> findAllByEmissoesAndData(BigDecimal emissoes, LocalDate data,
            Pageable pageable) {
                return historicoCarregamentoRepository.findAllByEmissoesAndData(emissoes, data, pageable);
    }

    public Page<HistoricoCarregamento> findAllByDataAndConsumo(LocalDate data, BigDecimal consumo, Pageable pageable) {
                return historicoCarregamentoRepository.findAllByDataAndConsumo(data, consumo, pageable);
    }

    public HistoricoCarregamento findById(Long id) {
        return historicoCarregamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado o histórico de carregamento com o id: " + id));
    }

    public HistoricoCarregamento create(HistoricoCarregamento historicoCarregamento) {
        return historicoCarregamentoRepository.save(historicoCarregamento);
    }

    public List<HistoricoCarregamento> findAllByUsuarioId(Long id) {
        return historicoCarregamentoRepository.findAllByUsuarioId(id);
    }

    public List<HistoricoCarregamento> findAllByPontoId(Long id) {
        return historicoCarregamentoRepository.findAllByPontoId(id);
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

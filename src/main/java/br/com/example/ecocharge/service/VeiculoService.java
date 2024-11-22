package br.com.example.ecocharge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.example.ecocharge.model.Veiculo;
import br.com.example.ecocharge.repository.VeiculoRepository;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }

    public Page<Veiculo> findAll(Pageable pageable) {
        return veiculoRepository.findAll(pageable);
    }

    public Page<Veiculo> findAllByModeloAndMarcaAndAnoAndAutonomia(String modelo, String marca,
            Integer ano, Integer autonomia, Pageable pageable) {
        return veiculoRepository.findAllByModeloAndMarcaAndAnoAndAutonomia(modelo, marca, ano, autonomia, pageable);

    }

    public Page<Veiculo> findAllByMarcaAndModelo(String marca, String modelo, Pageable pageable) {
        return veiculoRepository.findAllByMarcaAndModelo(marca, modelo, pageable);

    }

    public Page<Veiculo> findAllByAnoAndAutonomia(Integer ano, Integer autonomia,
            Pageable pageable) {
        return veiculoRepository.findAllByAnoAndAutonomia(ano, autonomia, pageable);

    }

    public Page<Veiculo> findAllByAutonomiaAndMarca(Integer autonomia, String marca, Pageable pageable) {
        return veiculoRepository.findAllByAutonomiaAndMarca(autonomia, marca, pageable);

    }

    public Page<Veiculo> findAllByAutonomiaAndAno(Integer autonomia, Integer ano, Pageable pageable) {
        return veiculoRepository.findAllByAutonomiaAndAno(autonomia, ano, pageable);
    }

    public List<Veiculo> findAllByAutonomia(int autonomia) {
        return veiculoRepository.findAllByAutonomia(autonomia);
    }

    public List<Veiculo> findAllByConector(String conector) {
        return veiculoRepository.findAllByConector(conector);
    }

    public Veiculo findById(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi encontrado o veículo com o id: " + id));
    }

    public Veiculo create(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public void deleteById(Long id) {
        veiculoRepository.deleteById(id);
    }

    public Veiculo update(Long id, Veiculo veiculo) {
        verificarId(id);
        veiculo.setId(id);
        return veiculoRepository.save(veiculo);
    }

    public void verificarId(Long id) {
        veiculoRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id não encontrado"));
    }
}

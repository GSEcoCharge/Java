package br.com.example.ecocharge.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.example.ecocharge.model.Veiculo;
import br.com.example.ecocharge.repository.VeiculoRepository;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }

    public Veiculo findById(Long id) {
        return veiculoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado o veículo com o id: " + id));
    }
    
    public Veiculo findByAutonomia(int autonomia) {
        return veiculoRepository.findByAutonomia(autonomia);
    }

    public Veiculo findByConector(String conector) {
        return veiculoRepository.findByConector(conector);
    }

    public Veiculo create(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public void deleteById(Long id) {
        veiculoRepository.deleteById(id);
    }
}

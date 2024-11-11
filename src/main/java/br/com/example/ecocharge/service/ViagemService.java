package br.com.example.ecocharge.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.example.ecocharge.model.Viagem;
import br.com.example.ecocharge.repository.ViagemRepository;

@Service
public class ViagemService {

    @Autowired
    private ViagemRepository viagemRepository;

    public List<Viagem> findAll() {
        return viagemRepository.findAll();
    }

    public Viagem findById(Long id) {
        return viagemRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrada a viagem com o id: " + id));
    }

    public Viagem create(Viagem viagem) {
        return viagemRepository.save(viagem);
    }

    public void deleteById(Long id) {
        viagemRepository.deleteById(id);
    }
}

package br.com.example.ecocharge.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.example.ecocharge.model.PontoParada;
import br.com.example.ecocharge.repository.PontoParadaRepository;

@Service
public class PontoParadaService {

    @Autowired
    private PontoParadaRepository pontoParadaRepository;

    public List<PontoParada> findAll() {
        return pontoParadaRepository.findAll();
    }

    public PontoParada findById(Long id) {
        return pontoParadaRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado o ponto de parada com o id: " + id));
    }

    public PontoParada create(PontoParada pontoParada) {
        return pontoParadaRepository.save(pontoParada);
    }

    public void deleteById(Long id) {
        pontoParadaRepository.deleteById(id);
    }

    public PontoParada update(Long id, PontoParada pontoParada) {
        verificarId(id);
        pontoParada.setId(id);
        return pontoParadaRepository.save(pontoParada);
    }

    public void verificarId(Long id){
        pontoParadaRepository.
        findById(id)
        .orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "id não encontrado")
        );
    }
}

package br.com.example.ecocharge.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.example.ecocharge.model.PostoCarregamento;
import br.com.example.ecocharge.repository.PostoCarregamentoRepository;

@Service
public class PostoCarregamentoService {

    @Autowired
    private PostoCarregamentoRepository postoCarregamentoRepository;

    public List<PostoCarregamento> findAll() {
        return postoCarregamentoRepository.findAll();
    }

    public PostoCarregamento findById(Long id) {
        return postoCarregamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado o posto de carregamento com o id: " + id));
    }

    public PostoCarregamento create(PostoCarregamento postoCarregamento) {
        return postoCarregamentoRepository.save(postoCarregamento);
    }

    public void deleteById(Long id) {
        postoCarregamentoRepository.deleteById(id);
    }
}

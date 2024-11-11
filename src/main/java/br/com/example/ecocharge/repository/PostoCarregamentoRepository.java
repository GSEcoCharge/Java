package br.com.example.ecocharge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.example.ecocharge.model.PostoCarregamento;

public interface PostoCarregamentoRepository extends JpaRepository<PostoCarregamento, Long> {
    
}

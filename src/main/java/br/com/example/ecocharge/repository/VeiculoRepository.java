package br.com.example.ecocharge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.example.ecocharge.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    @Query("SELECT v FROM Veiculo v WHERE v.autonomia = :autonomia")
    List<Veiculo> findAllByAutonomia(int autonomia);

    @Query("SELECT v FROM Veiculo v WHERE v.conector = :conector")
    List<Veiculo> findAllByConector(String conector);
    
}

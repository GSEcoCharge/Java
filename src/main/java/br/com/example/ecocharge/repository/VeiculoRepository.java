package br.com.example.ecocharge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.example.ecocharge.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    @Query("SELECT v FROM Veiculo v WHERE v.autonomia = :autonomia")
    Veiculo findByAutonomia(int autonomia);

    @Query("SELECT v FROM Veiculo v WHERE v.conector = :conector")
    Veiculo findByConector(String conector);
    
}

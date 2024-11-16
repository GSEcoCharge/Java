package br.com.example.ecocharge.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.example.ecocharge.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    @Query("SELECT v FROM Veiculo v WHERE v.autonomia = :autonomia")
    List<Veiculo> findAllByAutonomia(int autonomia);

    @Query("SELECT v FROM Veiculo v WHERE v.conector = :conector")
    List<Veiculo> findAllByConector(String conector);

    @Query("SELECT v FROM Veiculo v WHERE v.modelo = :modelo")
    List<Veiculo> findAllByModelo(String modelo);

    @Query("SELECT v FROM Veiculo v WHERE v.marca = :marca")
    List<Veiculo> findAllByMarca(String marca);

    @Query("SELECT v FROM Veiculo v WHERE v.ano = :ano")
    List<Veiculo> findAllByAno(int ano);

    @Query("SELECT v FROM Veiculo v WHERE v.modelo = :modelo AND v.marca = :marca AND v.ano = :ano AND v.autonomia = :autonomia")
    Page<Veiculo> findAllByModeloAndMarcaAndAnoAndAutonomia(String modelo, String marca, Integer ano,
            Integer autonomia, Pageable pageable);

    @Query("SELECT v FROM Veiculo v WHERE v.marca = :marca AND v.modelo = :modelo")
    Page<Veiculo> findAllByMarcaAndModelo(String marca, String modelo, Pageable pageable);

    @Query("SELECT v FROM Veiculo v WHERE v.ano = :ano AND v.autonomia = :autonomia")
    Page<Veiculo> findAllByAnoAndAutonomia(Integer ano, Integer autonomia, Pageable pageable);

    @Query("SELECT v FROM Veiculo v WHERE v.autonomia = :autonomia AND v.marca = :marca")
    Page<Veiculo> findAllByAutonomiaAndMarca(Integer autonomia, String marca, Pageable pageable);

    @Query("SELECT v FROM Veiculo v WHERE v.autonomia = :autonomia AND v.ano = :ano")
    Page<Veiculo> findAllByAutonomiaAndAno(Integer autonomia, Integer ano, Pageable pageable);

}

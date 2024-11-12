
package br.com.example.ecocharge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.ecocharge.model.Veiculo;
import br.com.example.ecocharge.service.VeiculoService;


@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public List<Veiculo> index() {
        return veiculoService.findAll();
    }

    @GetMapping("/autonomia/{autonomia}")
    public List<Veiculo> getVeiculosByAutonomia(@PathVariable int autonomia) {
        return veiculoService.findAllByAutonomia(autonomia);
    }

    @GetMapping("/conector/{conector}")
    public List<Veiculo> getVeiculosByConector(@PathVariable String conector) {
        return veiculoService.findAllByConector(conector);
    }    

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> getVeiculoById(@PathVariable Long id) {
        Veiculo veiculo = veiculoService.findById(id);
        return ResponseEntity.ok(veiculo);
    }

    @PostMapping
    public Veiculo createVeiculo(@RequestBody Veiculo veiculo) {
        return veiculoService.create(veiculo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id) {
        veiculoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

package br.com.example.ecocharge.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.example.ecocharge.model.PontoParada;
import br.com.example.ecocharge.service.PontoParadaService;

@RestController
@RequestMapping("/pontos-parada")
public class PontoParadaController {

    @Autowired
    private PontoParadaService pontoParadaService;

    @GetMapping
    public List<PontoParada> index() {
        return pontoParadaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PontoParada> getPontoParadaById(@PathVariable Long id) {
        PontoParada pontoParada = pontoParadaService.findById(id);
        return ResponseEntity.ok(pontoParada);
    }

    @PostMapping
    public PontoParada createPontoParada(@RequestBody PontoParada pontoParada) {
        return pontoParadaService.create(pontoParada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePontoParada(@PathVariable Long id) {
        pontoParadaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
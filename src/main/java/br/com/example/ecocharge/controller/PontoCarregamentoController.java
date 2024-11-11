
package br.com.example.ecocharge.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.example.ecocharge.model.PontoCarregamento;
import br.com.example.ecocharge.service.PontoCarregamentoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/pontos-carregamento")
public class PontoCarregamentoController {

    @Autowired
    private PontoCarregamentoService pontoCarregamentoService;

    @GetMapping
    public List<PontoCarregamento> index() {
        return pontoCarregamentoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PontoCarregamento> getPontoCarregamentoById(@PathVariable Long id) {
        PontoCarregamento pontoCarregamento = pontoCarregamentoService.findById(id);
        return ResponseEntity.ok(pontoCarregamento);
    }

    @GetMapping("/conector/{conector}")
    public PontoCarregamento getPontoCarregamentoByConector(@RequestParam String conector) {
        return pontoCarregamentoService.findByTipoConector(conector);
    }
    
    @GetMapping("/potencia/{potencia}")
    public PontoCarregamento getPontoCarregamentoByPotencia(@RequestParam int potencia) {
        return pontoCarregamentoService.findByVelocidadeCarregamento(potencia);
    }

    @GetMapping("/disponibilidade/{disponibilidade}")
    public PontoCarregamento getPontoCarregamentoByDisponibilidade(@RequestParam String disponibilidade) {
        return pontoCarregamentoService.findByDisponibilidade(disponibilidade);
    }
    
    @GetMapping("/reservavel/{reservavel}")
    public PontoCarregamento getPontoCarregamentoByReservavel(@RequestParam char reservavel) {
        return pontoCarregamentoService.findByReservavel(reservavel);
    }

    @PostMapping
    public PontoCarregamento createPontoCarregamento(@RequestBody PontoCarregamento pontoCarregamento) {
        return pontoCarregamentoService.create(pontoCarregamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePontoCarregamento(@PathVariable Long id) {
        pontoCarregamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
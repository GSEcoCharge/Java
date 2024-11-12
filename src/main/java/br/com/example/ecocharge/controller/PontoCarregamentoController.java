
package br.com.example.ecocharge.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.example.ecocharge.model.PontoCarregamento;
import br.com.example.ecocharge.service.PontoCarregamentoService;
import org.springframework.web.bind.annotation.GetMapping;


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
    public List<PontoCarregamento> getPontosCarregamentoByConector(@PathVariable String conector) {
        return pontoCarregamentoService.findAllByTipoConector(conector);
    }
    
    @GetMapping("/potencia/{potencia}")
    public List<PontoCarregamento> getPontosCarregamentoByPotencia(@PathVariable int potencia) {
        return pontoCarregamentoService.findAllByVelocidadeCarregamento(potencia);
    }

    @GetMapping("/disponibilidade/{disponibilidade}")
    public List<PontoCarregamento> getPontosCarregamentoByDisponibilidade(@PathVariable String disponibilidade) {
        return pontoCarregamentoService.findAllByDisponibilidade(disponibilidade);
    }
    
    @GetMapping("/reservavel/{reservavel}")
    public List<PontoCarregamento> getPontosCarregamentoByReservavel(@PathVariable char reservavel) {
        return pontoCarregamentoService.findAllByReservavel(reservavel);
    }

    @PostMapping
    public PontoCarregamento createPontoCarregamento(@RequestBody PontoCarregamento pontoCarregamento) {
        return pontoCarregamentoService.create(pontoCarregamento);
    }

    @DeleteMapping("/perfil/{id}")
    public ResponseEntity<Void> deletePontoCarregamento(@PathVariable Long id) {
        pontoCarregamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
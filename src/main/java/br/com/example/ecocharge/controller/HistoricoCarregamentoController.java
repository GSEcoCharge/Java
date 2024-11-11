
package br.com.example.ecocharge.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.example.ecocharge.model.HistoricoCarregamento;
import br.com.example.ecocharge.service.HistoricoCarregamentoService;

@RestController
@RequestMapping("/historicos-carregamento")
public class HistoricoCarregamentoController {

    @Autowired
    private HistoricoCarregamentoService historicoCarregamentoService;

    @GetMapping
    public List<HistoricoCarregamento> getAllHistoricosCarregamento() {
        return historicoCarregamentoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoricoCarregamento> getHistoricoCarregamentoById(@PathVariable Long id) {
        HistoricoCarregamento historicoCarregamento = historicoCarregamentoService.findById(id);
        return ResponseEntity.ok(historicoCarregamento);
    }

    @PostMapping
    public HistoricoCarregamento createHistoricoCarregamento(@RequestBody HistoricoCarregamento historicoCarregamento) {
        return historicoCarregamentoService.create(historicoCarregamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistoricoCarregamento(@PathVariable Long id) {
        historicoCarregamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
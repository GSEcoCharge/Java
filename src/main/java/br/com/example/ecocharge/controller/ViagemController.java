
package br.com.example.ecocharge.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.example.ecocharge.model.Viagem;
import br.com.example.ecocharge.service.ViagemService;

@RestController
@RequestMapping("/viagens")
public class ViagemController {

    @Autowired
    private ViagemService viagemService;

    @GetMapping
    public List<Viagem> index() {
        return viagemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viagem> getViagemById(@PathVariable Long id) {
        Viagem viagem = viagemService.findById(id);
        return ResponseEntity.ok(viagem);
    }

    @PostMapping
    public Viagem createViagem(@RequestBody Viagem viagem) {
        return viagemService.create(viagem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViagem(@PathVariable Long id) {
        viagemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
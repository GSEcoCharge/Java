
package br.com.example.ecocharge.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.example.ecocharge.model.PostoCarregamento;
import br.com.example.ecocharge.service.PostoCarregamentoService;

@RestController
@RequestMapping("/postos")
public class PostoCarregamentoController {

    @Autowired
    private PostoCarregamentoService postoCarregamentoService;

    @GetMapping
    public List<PostoCarregamento> index() {
        return postoCarregamentoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostoCarregamento> getPostoById(@PathVariable Long id) {
        PostoCarregamento posto = postoCarregamentoService.findById(id);
        return ResponseEntity.ok(posto);
    }

    @PostMapping
    public PostoCarregamento createPosto(@RequestBody PostoCarregamento posto) {
        return postoCarregamentoService.create(posto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosto(@PathVariable Long id) {
        postoCarregamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
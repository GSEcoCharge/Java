
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

import br.com.example.ecocharge.model.Avaliacao;
import br.com.example.ecocharge.service.AvaliacaoService;


@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public List<Avaliacao> index() {
        return avaliacaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> getAvaliacaoById(@PathVariable Long id) {
        Avaliacao avaliacao = avaliacaoService.findById(id);
        return ResponseEntity.ok(avaliacao);
    }

    @PostMapping
    public Avaliacao createAvaliacao(@RequestBody Avaliacao avaliacao) {
        return avaliacaoService.create(avaliacao);
    }

    @GetMapping("/usuario/{id}")
    public Avaliacao getAvaliacaoByUsuario(@PathVariable Long id) {
        return avaliacaoService.findByUsuarioId(id);
    }

    @GetMapping("/posto/{id}")
    public Avaliacao getAvaliacaoByPosto(@PathVariable Long id) {
        return avaliacaoService.findByPostoId(id);
    }    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable Long id) {
        avaliacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
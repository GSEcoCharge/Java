package br.com.example.ecocharge.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.example.ecocharge.model.PontoParada;
import br.com.example.ecocharge.service.PontoParadaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pontos-parada")
@Tag(name = "pontos-parada", description = "Endpoint relacionado com pontos de parada")
public class PontoParadaController {

    @Autowired
    private PontoParadaService pontoParadaService;

    @GetMapping
    @Operation(summary = "Lista todos os pontos de parada cadastrados no sistema.", description = "Endpoint que retorna uma lista de objetos do tipo ponto de parada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pontos de parada retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<PontoParada> index() {
        return pontoParadaService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um ponto de parada específico cadastrado no sistema.", description = "Endpoint que retorna um objeto do tipo ponto de parada com um id informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ponto de parada encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Ponto de parada não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PontoParada> getPontoParadaById(@PathVariable Long id) {
        PontoParada pontoParada = pontoParadaService.findById(id);
        return ResponseEntity.ok(pontoParada);
    }

    @PostMapping
    @Operation(summary = "Cria um novo ponto de parada.", description = "Endpoint para criar um novo ponto de parada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Ponto de parada cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação do ponto de parada"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public PontoParada createPontoParada(@RequestBody PontoParada pontoParada) {
        return pontoParadaService.create(pontoParada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um ponto de parada existente.", description = "Endpoint para atualizar um ponto de parada com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ponto de parada atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação do ponto de parada"),
        @ApiResponse(responseCode = "404", description = "Ponto de parada não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PontoParada> updatePontoParada(@PathVariable Long id, @RequestBody PontoParada pontoParada) {
        PontoParada updatedPontoParada = pontoParadaService.update(id, pontoParada);
        return ResponseEntity.ok(updatedPontoParada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um ponto de parada pelo ID.", description = "Endpoint que deleta um ponto de parada com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Ponto de parada deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Ponto de parada não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deletePontoParada(@PathVariable Long id) {
        pontoParadaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
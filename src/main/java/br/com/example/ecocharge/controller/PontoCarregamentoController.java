package br.com.example.ecocharge.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.example.ecocharge.model.PontoCarregamento;
import br.com.example.ecocharge.service.PontoCarregamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pontos-carregamento")
@Tag(name = "pontos-carregamento", description = "Endpoint relacionado com pontos de carregamento")
public class PontoCarregamentoController {

    @Autowired
    private PontoCarregamentoService pontoCarregamentoService;

    @GetMapping
    @Operation(summary = "Lista todos os pontos de carregamento cadastrados no sistema.", description = "Endpoint que retorna uma lista de objetos do tipo ponto de carregamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pontos de carregamento retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<PontoCarregamento> index() {
        return pontoCarregamentoService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um ponto de carregamento específico cadastrado no sistema.", description = "Endpoint que retorna um objeto do tipo ponto de carregamento com um id informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ponto de carregamento encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Ponto de carregamento não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PontoCarregamento> getPontoCarregamentoById(@PathVariable Long id) {
        PontoCarregamento pontoCarregamento = pontoCarregamentoService.findById(id);
        return ResponseEntity.ok(pontoCarregamento);
    }

    @GetMapping("/conector/{conector}")
    @Operation(summary = "Lista todos os pontos de carregamento por tipo de conector.", description = "Endpoint que retorna uma lista de objetos do tipo ponto de carregamento com um tipo de conector informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pontos de carregamento retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<PontoCarregamento> getPontosCarregamentoByConector(@PathVariable String conector) {
        return pontoCarregamentoService.findAllByTipoConector(conector);
    }

    @GetMapping("/potencia/{potencia}")
    @Operation(summary = "Lista todos os pontos de carregamento por potência.", description = "Endpoint que retorna uma lista de objetos do tipo ponto de carregamento com uma potência informada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pontos de carregamento retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<PontoCarregamento> getPontosCarregamentoByPotencia(@PathVariable int potencia) {
        return pontoCarregamentoService.findAllByVelocidadeCarregamento(potencia);
    }

    @GetMapping("/disponibilidade/{disponibilidade}")
    @Operation(summary = "Lista todos os pontos de carregamento por disponibilidade.", description = "Endpoint que retorna uma lista de objetos do tipo ponto de carregamento com uma disponibilidade informada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pontos de carregamento retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<PontoCarregamento> getPontosCarregamentoByDisponibilidade(@PathVariable String disponibilidade) {
        return pontoCarregamentoService.findAllByDisponibilidade(disponibilidade);
    }

    @GetMapping("/reservavel/{reservavel}")
    @Operation(summary = "Lista todos os pontos de carregamento por reservabilidade.", description = "Endpoint que retorna uma lista de objetos do tipo ponto de carregamento com uma reservabilidade informada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pontos de carregamento retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<PontoCarregamento> getPontosCarregamentoByReservavel(@PathVariable char reservavel) {
        return pontoCarregamentoService.findAllByReservavel(reservavel);
    }

    @PostMapping
    @Operation(summary = "Cria um novo ponto de carregamento.", description = "Endpoint para criar um novo ponto de carregamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Ponto de carregamento cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação do ponto de carregamento"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public PontoCarregamento createPontoCarregamento(@RequestBody PontoCarregamento pontoCarregamento) {
        return pontoCarregamentoService.create(pontoCarregamento);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um ponto de carregamento existente.", description = "Endpoint para atualizar um ponto de carregamento com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ponto de carregamento atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação do ponto de carregamento"),
        @ApiResponse(responseCode = "404", description = "Ponto de carregamento não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PontoCarregamento> updatePontoCarregamento(@PathVariable Long id, @RequestBody PontoCarregamento pontoCarregamento) {
        PontoCarregamento updatedPontoCarregamento = pontoCarregamentoService.update(id, pontoCarregamento);
        return ResponseEntity.ok(updatedPontoCarregamento);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um ponto de carregamento pelo ID.", description = "Endpoint que deleta um ponto de carregamento com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Ponto de carregamento deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Ponto de carregamento não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deletePontoCarregamento(@PathVariable Long id) {
        pontoCarregamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
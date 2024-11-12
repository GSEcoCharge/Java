package br.com.example.ecocharge.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.example.ecocharge.model.HistoricoCarregamento;
import br.com.example.ecocharge.service.HistoricoCarregamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/historicos-carregamento")
@Tag(name = "historicos-carregamento", description = "Endpoint relacionado com históricos de carregamento")
public class HistoricoCarregamentoController {

    @Autowired
    private HistoricoCarregamentoService historicoCarregamentoService;

    @GetMapping
    @Operation(summary = "Lista todos os históricos de carregamento.", description = "Endpoint que retorna uma lista de objetos do tipo histórico de carregamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de históricos de carregamento retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<HistoricoCarregamento> getAllHistoricosCarregamento() {
        return historicoCarregamentoService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um histórico de carregamento específico.", description = "Endpoint que retorna um objeto do tipo histórico de carregamento com um id informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Histórico de carregamento encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Histórico de carregamento não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<HistoricoCarregamento> getHistoricoCarregamentoById(@PathVariable Long id) {
        HistoricoCarregamento historicoCarregamento = historicoCarregamentoService.findById(id);
        return ResponseEntity.ok(historicoCarregamento);
    }

    @PostMapping
    @Operation(summary = "Cria um novo histórico de carregamento.", description = "Endpoint para criar um novo histórico de carregamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Histórico de carregamento criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação do histórico de carregamento"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public HistoricoCarregamento createHistoricoCarregamento(@RequestBody HistoricoCarregamento historicoCarregamento) {
        return historicoCarregamentoService.create(historicoCarregamento);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um histórico de carregamento existente.", description = "Endpoint para atualizar um histórico de carregamento com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Histórico de carregamento atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação do histórico de carregamento"),
        @ApiResponse(responseCode = "404", description = "Histórico de carregamento não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<HistoricoCarregamento> updateHistoricoCarregamento(@PathVariable Long id, @RequestBody HistoricoCarregamento historicoCarregamento) {
        HistoricoCarregamento updatedHistoricoCarregamento = historicoCarregamentoService.update(id, historicoCarregamento);
        return ResponseEntity.ok(updatedHistoricoCarregamento);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um histórico de carregamento pelo ID.", description = "Endpoint que deleta um histórico de carregamento com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Histórico de carregamento deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Histórico de carregamento não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deleteHistoricoCarregamento(@PathVariable Long id) {
        historicoCarregamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
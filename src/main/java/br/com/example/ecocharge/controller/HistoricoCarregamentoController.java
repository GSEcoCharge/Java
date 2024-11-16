package br.com.example.ecocharge.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    PagedResourcesAssembler<HistoricoCarregamento> pagedResourcesAssembler;

    @GetMapping
    @Operation(summary = "Lista todos os históricos de carregamento.", description = "Endpoint que retorna uma lista de objetos do tipo histórico de carregamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de históricos de carregamento retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public PagedModel<EntityModel<HistoricoCarregamento>> index(
        @RequestParam(required = false) LocalDate data,
        @RequestParam(required = false) BigDecimal consumo,
        @RequestParam(required = false) BigDecimal emissoes,
        @ParameterObject @PageableDefault(size = 10, sort = "id") Pageable pageable
    ) {
        Page<HistoricoCarregamento> page = null;

        if (data != null && consumo != null && emissoes != null) {
            page = historicoCarregamentoService.findAllByDataAndConsumoAndEmissoes(data, consumo, emissoes, pageable);
        } else if (consumo != null && emissoes != null) {
            page = historicoCarregamentoService.findAllByConsumoAndEmissoes(consumo, emissoes, pageable);
        } else if (emissoes != null && data != null) {
            page = historicoCarregamentoService.findAllByEmissoesAndData(emissoes, data, pageable);
        } else if (data != null && consumo != null) {
            page = historicoCarregamentoService.findAllByDataAndConsumo(data, consumo, pageable);
        } else {
            page = historicoCarregamentoService.findAll(pageable);
        }
        return pagedResourcesAssembler.toModel(page);
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

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Lista todos os históricos de carregamento por usuário.", description = "Endpoint que retorna uma lista de históricos de carregamento para um usuário específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de históricos de carregamento retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<HistoricoCarregamento> getHistoricosCarregamentoByUsuarioId(@PathVariable Long usuarioId) {
        return historicoCarregamentoService.findAllByUsuarioId(usuarioId);
    }

    @GetMapping("/ponto/{pontoId}")
    @Operation(summary = "Lista todos os históricos de carregamento por ponto.", description = "Endpoint que retorna uma lista de históricos de carregamento para um ponto específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de históricos de carregamento retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Ponto não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<HistoricoCarregamento> getHistoricosCarregamentoByPontoId(@PathVariable Long pontoId) {
        return historicoCarregamentoService.findAllByPontoId(pontoId);
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
package br.com.example.ecocharge.controller;

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

    @Autowired
    PagedResourcesAssembler<PontoCarregamento> pageAssembler;

    @GetMapping
    @Operation(summary = "Lista todos os pontos de carregamento cadastrados no sistema.", description = "Endpoint que retorna uma lista de objetos do tipo ponto de carregamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pontos de carregamento retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public PagedModel<EntityModel<PontoCarregamento>> index(
        @RequestParam(required = false) String disponibilidade,
        @RequestParam(required = false) String tipoConector,
        @RequestParam(required = false) Integer velocidadeCarregamento,
        @RequestParam(required = false) Character reservavel,
        @ParameterObject @PageableDefault(size = 10, sort = "id") Pageable pageable) {

        Page<PontoCarregamento> page = null;
        
        if( velocidadeCarregamento != null && tipoConector != null && reservavel != null) {
            page = pontoCarregamentoService.findAllByTipoConectorAndPotenciaAndReservavel(tipoConector, velocidadeCarregamento, reservavel, pageable);
        }  else if (tipoConector != null && reservavel != null) {
            page = pontoCarregamentoService.findAllByTipoConectorAndReservavel(tipoConector, reservavel, pageable);
        } else if (disponibilidade != null) {
            page = pontoCarregamentoService.findAllByDisponibilidade(disponibilidade, pageable);
        } else if (tipoConector != null) {
            page = pontoCarregamentoService.findAllByTipoConector(tipoConector, pageable);
        } else if (velocidadeCarregamento != null) {
            page = pontoCarregamentoService.findAllByVelocidadeCarregamento(velocidadeCarregamento, pageable);
        } else if (reservavel != null) {
            page = pontoCarregamentoService.findAllByReservavel(reservavel, pageable);
        } else {
            page = pontoCarregamentoService.findAll(pageable);
        }
    
        return pageAssembler.toModel(page, pontoCarregamento -> EntityModel.of(pontoCarregamento));
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
    public PagedModel<EntityModel<PontoCarregamento>> getPontosCarregamentoByConector(@PathVariable String conector,
    @ParameterObject @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<PontoCarregamento> page = pontoCarregamentoService.findAllByTipoConector(conector, pageable);
        return pageAssembler.toModel(page, pontoCarregamento -> EntityModel.of(pontoCarregamento));
    }

    @GetMapping("/potencia/{potencia}")
    @Operation(summary = "Lista todos os pontos de carregamento por potência.", description = "Endpoint que retorna uma lista de objetos do tipo ponto de carregamento com uma potência informada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pontos de carregamento retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public PagedModel<EntityModel<PontoCarregamento>> getPontosCarregamentoByPotencia(@PathVariable int potencia,
    @ParameterObject @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<PontoCarregamento> page = pontoCarregamentoService.findAllByVelocidadeCarregamento(potencia, pageable);
        return pageAssembler.toModel(page, pontoCarregamento -> EntityModel.of(pontoCarregamento));
    }

    @GetMapping("/disponibilidade/{disponibilidade}")
    @Operation(summary = "Lista todos os pontos de carregamento por disponibilidade.", description = "Endpoint que retorna uma lista de objetos do tipo ponto de carregamento com uma disponibilidade informada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pontos de carregamento retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public PagedModel<EntityModel<PontoCarregamento>> getPontosCarregamentoByDisponibilidade(@PathVariable String disponibilidade,
    @ParameterObject @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<PontoCarregamento> page = pontoCarregamentoService.findAllByDisponibilidade(disponibilidade, pageable);
        return pageAssembler.toModel(page, pontoCarregamento -> EntityModel.of(pontoCarregamento));
    }

    @GetMapping("/reservavel/{reservavel}")
    @Operation(summary = "Lista todos os pontos de carregamento por reservabilidade.", description = "Endpoint que retorna uma lista de objetos do tipo ponto de carregamento com uma reservabilidade informada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de pontos de carregamento retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public PagedModel<EntityModel<PontoCarregamento>> getPontosCarregamentoByReservavel(@PathVariable char reservavel,
    @ParameterObject @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<PontoCarregamento> page =  pontoCarregamentoService.findAllByReservavel(reservavel, pageable);
        return pageAssembler.toModel(page, pontoCarregamento -> EntityModel.of(pontoCarregamento));
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
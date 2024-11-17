package br.com.example.ecocharge.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.time.LocalDate;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.ecocharge.model.Avaliacao;
import br.com.example.ecocharge.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/avaliacoes")
@Tag(name = "avaliacoes", description = "Endpoint relacionado com avaliações")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    PagedResourcesAssembler<Avaliacao> pagedResourcesAssembler;

    @GetMapping
    @Operation(summary = "Lista todas as avaliações cadastradas no sistema.", description = "Endpoint que retorna uma lista de objetos do tipo avaliação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de avaliações retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public PagedModel<EntityModel<Avaliacao>> index(
        @RequestParam(required = false) Integer nota,
        @RequestParam(required = false) LocalDate data,
        @ParameterObject @PageableDefault(size = 10, sort = "data", direction = Direction.DESC) Pageable pageable
    ) {
        Page<Avaliacao> page = null;

        if (nota != null && data != null) {
            page = avaliacaoService.findAllByNotaAndData(nota, data, pageable);
        } else if (nota != null) {
            page = avaliacaoService.findAllByNota(nota, pageable);
        } else if (data != null) {
            page = avaliacaoService.findAllByData(data, pageable);
        } else {
            page = avaliacaoService.findAll(pageable);
        }
        return pagedResourcesAssembler.toModel(page);        
    }

    @GetMapping("/usuario")
    @Operation(summary = "Lista todas as avaliações de um usuário específico.", description = "Endpoint que retorna uma lista de objetos do tipo avaliação para um usuário específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de avaliações retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public PagedModel<EntityModel<Avaliacao>> getAvaliacoesByUsuario(
        @RequestParam(required = true) Long id,
        @RequestParam(required = false) Integer nota,
        @RequestParam(required = false) LocalDate data,
        @ParameterObject @PageableDefault(size = 10, sort = "data", direction = Direction.DESC) Pageable pageable
    ) {
        Page<Avaliacao> page = null;

        if (nota != null && data != null) {
            page = avaliacaoService.findAllByIdUsuarioWithNotaAndData(id, nota, data, pageable);
        } else if (nota != null) {
            page = avaliacaoService.findAllByIdUsuarioWithNota(id, nota, pageable);
        } else if (data != null) {
            page = avaliacaoService.findAllByIdUsuarioWithData(id, data, pageable);
        } else {
            page = avaliacaoService.findAllByUsuarioId(id, pageable);
        }
        return pagedResourcesAssembler.toModel(page);
}

    @GetMapping("/posto")
    @Operation(summary = "Lista todas as avaliações de um posto específico.", description = "Endpoint que retorna uma lista de objetos do tipo avaliação para um posto específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de avaliações retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public PagedModel<EntityModel<Avaliacao>> getAvaliacoesByPosto(
        @RequestParam(required = true) Long id,
        @RequestParam(required = false) Integer nota,
        @RequestParam(required = false) LocalDate data,
        @ParameterObject @PageableDefault(size = 10, sort = "data", direction = Direction.DESC) Pageable pageable
        ) {
            Page<Avaliacao> page = null;
            if (nota != null && data != null) {
                page = avaliacaoService.findAllByIdPostoWithNotaAndData(id, nota, data, pageable);
            } else if (nota != null) {
                page = avaliacaoService.findAllByIdPostoWithNota(id, nota, pageable);
            } else if (data != null) {
                page = avaliacaoService.findAllByIdPostoWithData(id, data, pageable);
            } else {
                page = avaliacaoService.findAllByPostoId(id, pageable);
            }
            return pagedResourcesAssembler.toModel(page);
    } 

    @GetMapping("/{id}")
    @Operation(summary = "Retorna uma avaliação específica cadastrada no sistema.", description = "Endpoint que retorna um objeto do tipo avaliação com um id informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avaliação encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Avaliação não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Avaliacao> getAvaliacaoById(@PathVariable Long id) {
        Avaliacao avaliacao = avaliacaoService.findById(id);
        return ResponseEntity.ok(avaliacao);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Cria uma nova avaliação.", description = "Endpoint para criar uma nova avaliação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Avaliação cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação da avaliação"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Avaliacao createAvaliacao(@RequestBody Avaliacao avaliacao) {
        return avaliacaoService.create(avaliacao);
    }   

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma avaliação existente.", description = "Endpoint para atualizar uma avaliação existente com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avaliação atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação da avaliação"),
        @ApiResponse(responseCode = "404", description = "Avaliação não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Avaliacao> updateAvaliacao(@PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        Avaliacao updatedAvaliacao = avaliacaoService.update(id, avaliacao);
        return ResponseEntity.ok(updatedAvaliacao);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Deleta uma avaliação pelo ID.", description = "Endpoint que deleta uma avaliação com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Avaliação deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Avaliação não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable Long id) {
        avaliacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
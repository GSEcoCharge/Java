package br.com.example.ecocharge.controller;

import java.util.List;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import br.com.example.ecocharge.model.Avaliacao;
import br.com.example.ecocharge.service.AvaliacaoService;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/avaliacoes")
@Tag(name = "avaliacoes", description = "Endpoint relacionado com avaliações")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    @Operation(summary = "Lista todas as avaliações cadastradas no sistema.", description = "Endpoint que retorna uma lista de objetos do tipo avaliação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de avaliações retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<Avaliacao> index() {
        return avaliacaoService.findAll();
    }

    @GetMapping("/usuario/{id}")
    @Operation(summary = "Lista todas as avaliações de um usuário específico.", description = "Endpoint que retorna uma lista de objetos do tipo avaliação para um usuário específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de avaliações retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<Avaliacao> getAvaliacoesByUsuario(@PathVariable Long id) {
        return avaliacaoService.findAllByUsuarioId(id);
    }

    @GetMapping("/posto/{id}")
    @Operation(summary = "Lista todas as avaliações de um posto específico.", description = "Endpoint que retorna uma lista de objetos do tipo avaliação para um posto específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de avaliações retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<Avaliacao> getAvaliacoesByPosto(@PathVariable Long id) {
        return avaliacaoService.findAllByPostoId(id);
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
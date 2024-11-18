package br.com.example.ecocharge.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.example.ecocharge.model.PostoCarregamento;
import br.com.example.ecocharge.service.PostoCarregamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RequestMapping("/postos")
@Tag(name = "postos", description = "Endpoint relacionado com postos de carregamento")
public class PostoCarregamentoController {

    @Autowired
    private PostoCarregamentoService postoCarregamentoService;

    @GetMapping
    @Operation(summary = "Lista todos os postos de carregamento cadastrados no sistema.", description = "Endpoint que retorna uma lista de objetos do tipo posto de carregamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de postos de carregamento retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<PostoCarregamento> index() {
        return postoCarregamentoService.findAll();
    }
    
    @PostMapping
    @ResponseStatus(CREATED)

    @Operation(summary = "Cria um novo posto de carregamento.", description = "Endpoint para criar um novo posto de carregamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Posto de carregamento cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação do posto de carregamento"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public PostoCarregamento createPosto(@RequestBody PostoCarregamento posto) {
        return postoCarregamentoService.create(posto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um posto de carregamento específico cadastrado no sistema.", description = "Endpoint que retorna um objeto do tipo posto de carregamento com um id informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Posto de carregamento encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Posto de carregamento não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PostoCarregamento> getPostoById(@PathVariable Long id) {
        PostoCarregamento posto = postoCarregamentoService.findById(id);
        return ResponseEntity.ok(posto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um posto de carregamento existente.", description = "Endpoint para atualizar um posto de carregamento existente com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Posto de carregamento atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Posto de carregamento não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PostoCarregamento> updatePosto(@PathVariable Long id, @RequestBody PostoCarregamento postoDetails) {
        PostoCarregamento updatedPosto = postoCarregamentoService.update(id, postoDetails);
        return ResponseEntity.ok(updatedPosto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Deleta um posto de carregamento pelo ID.", description = "Endpoint que deleta um posto de carregamento com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Posto de carregamento deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Posto de carregamento não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deletePosto(@PathVariable Long id) {
        postoCarregamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
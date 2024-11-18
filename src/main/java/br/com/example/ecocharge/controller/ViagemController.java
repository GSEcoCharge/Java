package br.com.example.ecocharge.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.example.ecocharge.dto.ViagemResponse;
import br.com.example.ecocharge.model.Viagem;
import br.com.example.ecocharge.service.ViagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/viagens")
@Tag(name = "viagens", description = "Endpoint relacionado com viagens")
public class ViagemController {

    @Autowired
    private ViagemService viagemService;

    @GetMapping
    @Operation(summary = "Lista todas as viagens cadastradas no sistema.", description = "Endpoint que retorna uma lista de objetos do tipo viagem")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de viagens retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<ViagemResponse> index() {
        List<Viagem> viagens = viagemService.findAll();
        return viagens.stream().map(ViagemResponse::from).toList();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Cria uma nova viagem.", description = "Endpoint para criar uma nova viagem")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Viagem cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação da viagem"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Viagem createViagem(@RequestBody Viagem viagem) {
        return viagemService.create(viagem);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna uma viagem específica cadastrada no sistema.", description = "Endpoint que retorna um objeto do tipo viagem com um id informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Viagem encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Viagem não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Viagem> getViagemById(@PathVariable Long id) {
        Viagem viagem = viagemService.findById(id);
        return ResponseEntity.ok(viagem);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma viagem existente.", description = "Endpoint para atualizar uma viagem existente com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Viagem atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Viagem não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Viagem> updateViagem(@PathVariable Long id, @RequestBody Viagem viagemDetails) {
        Viagem updatedViagem = viagemService.update(id, viagemDetails);
        return ResponseEntity.ok(updatedViagem);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Deleta uma viagem pelo ID.", description = "Endpoint que deleta uma viagem com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Viagem deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Viagem não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deleteViagem(@PathVariable Long id) {
        viagemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
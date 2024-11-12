package br.com.example.ecocharge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.ecocharge.model.Veiculo;
import br.com.example.ecocharge.service.VeiculoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/veiculos")
@Tag(name = "veiculos", description = "Endpoint relacionado com veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @Operation(summary = "Listar todos os veículos", description = "Endpoint para listar todos os veículos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Veículos listados com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public List<Veiculo> index() {
        return veiculoService.findAll();
    }

    @Operation(summary = "Buscar veículos por autonomia", description = "Endpoint para buscar veículos por autonomia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Veículos encontrados com sucesso"),
        @ApiResponse(responseCode = "404", description = "Veículos não encontrados"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/autonomia/{autonomia}")
    public List<Veiculo> getVeiculosByAutonomia(@PathVariable int autonomia) {
        return veiculoService.findAllByAutonomia(autonomia);
    }

    @Operation(summary = "Buscar veículos por conector", description = "Endpoint para buscar veículos por conector")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Veículos encontrados com sucesso"),
        @ApiResponse(responseCode = "404", description = "Veículos não encontrados"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/conector/{conector}")
    public List<Veiculo> getVeiculosByConector(@PathVariable String conector) {
        return veiculoService.findAllByConector(conector);
    }    

    @Operation(summary = "Buscar veículo por ID", description = "Endpoint para buscar um veículo por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Veículo encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> getVeiculoById(@PathVariable Long id) {
        Veiculo veiculo = veiculoService.findById(id);
        return ResponseEntity.ok(veiculo);
    }

    @Operation(summary = "Criar um novo veículo", description = "Endpoint para criar um novo veículo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Veículo criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação do veículo"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public Veiculo createVeiculo(@RequestBody Veiculo veiculo) {
        return veiculoService.create(veiculo);
    }

    @Operation(summary = "Atualizar um veículo existente", description = "Endpoint para atualizar um veículo existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Veículo atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação do veículo"),
        @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> updateVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        Veiculo updatedVeiculo = veiculoService.update(id, veiculo);
        return ResponseEntity.ok(updatedVeiculo);
    }

    @Operation(summary = "Deletar um veículo", description = "Endpoint para deletar um veículo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Veículo deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id) {
        veiculoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
package br.com.example.ecocharge.controller;

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

import br.com.example.ecocharge.dto.VeiculoResponse;
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

    @Autowired
    PagedResourcesAssembler<VeiculoResponse> pageAssembler;

    @Operation(summary = "Listar todos os veículos", description = "Endpoint para listar todos os veículos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Veículos listados com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public PagedModel<EntityModel<VeiculoResponse>> index(
        @RequestParam(required = false) String modelo,
        @RequestParam(required = false) String marca,
        @RequestParam(required = false) Integer ano,
        @RequestParam(required = false) Integer autonomia,
        @ParameterObject @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        
        Page<Veiculo> page = null;

        if(modelo != null && marca != null && ano != null && autonomia != null) {
            page = veiculoService.findAllByModeloAndMarcaAndAnoAndAutonomia(modelo, marca, ano, autonomia, pageable);
        } else if(marca != null && modelo != null) {
            page = veiculoService.findAllByMarcaAndModelo(marca, modelo, pageable);
        } else if(ano != null && autonomia != null) {
            page = veiculoService.findAllByAnoAndAutonomia(ano, autonomia, pageable);
        } else if(autonomia != null && marca != null) {
            page = veiculoService.findAllByAutonomiaAndMarca(autonomia, marca, pageable);
        } else if(autonomia != null && ano != null) {
            page = veiculoService.findAllByAutonomiaAndAno(autonomia, ano, pageable);
        } else{
            page = veiculoService.findAll(pageable);
        }
        
        return pageAssembler.toModel(page.map(VeiculoResponse::from));
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

    @Operation(summary = "Buscar veículos por autonomia", description = "Endpoint para buscar veículos por autonomia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Veículos encontrados com sucesso"),
        @ApiResponse(responseCode = "404", description = "Veículos não encontrados"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/autonomia/{autonomia}")
    public ResponseEntity<List<VeiculoResponse>> getVeiculosByAutonomia(@PathVariable int autonomia) {
        List<Veiculo> veiculos = veiculoService.findAllByAutonomia(autonomia);
        if (veiculos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<VeiculoResponse> veiculoResponses = veiculos.stream()
            .map(VeiculoResponse::from)
            .toList();
        return ResponseEntity.ok(veiculoResponses);
    }

    @Operation(summary = "Buscar veículos por conector", description = "Endpoint para buscar veículos por conector")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Veículos encontrados com sucesso"),
        @ApiResponse(responseCode = "404", description = "Veículos não encontrados"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/conector/{conector}")
    public ResponseEntity<List<VeiculoResponse>> getVeiculosByConector(@PathVariable String conector) {
        List<Veiculo> veiculos = veiculoService.findAllByConector(conector);
        if (veiculos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<VeiculoResponse> veiculoResponses = veiculos.stream()
            .map(VeiculoResponse::from)
            .toList();
        return ResponseEntity.ok(veiculoResponses);
    }    

    @Operation(summary = "Buscar veículo por ID", description = "Endpoint para buscar um veículo por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Veículo encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Veículo não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponse> getVeiculoById(@PathVariable Long id) {
        Veiculo veiculo = veiculoService.findById(id);
        return ResponseEntity.ok(VeiculoResponse.from(veiculo));
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
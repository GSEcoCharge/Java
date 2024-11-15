package br.com.example.ecocharge.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.ecocharge.model.Reserva;
import br.com.example.ecocharge.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/reservas")
@Tag(name = "reservas", description = "Endpoint relacionado com reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    @Operation(summary = "Lista todas as reservas cadastradas no sistema.", description = "Endpoint que retorna uma lista de objetos do tipo reserva")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de reservas retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<Reserva> index() {
        return reservaService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna uma reserva específica cadastrada no sistema.", description = "Endpoint que retorna um objeto do tipo reserva com um id informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reserva encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Reserva não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        Reserva reserva = reservaService.findById(id);
        return ResponseEntity.ok(reserva);
    }

    @GetMapping("/usuario/{id}")
    @Operation(summary = "Lista todas as reservas de um usuário específico.", description = "Endpoint que retorna uma lista de reservas de um usuário com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de reservas retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<Reserva> getReservasByUsuarioId(@PathVariable Long id) {
        return reservaService.findAllByUsuarioId(id);
    }

    @GetMapping("/ponto/{id}")
    @Operation(summary = "Lista todas as reservas de um ponto específico.", description = "Endpoint que retorna uma lista de reservas de um ponto com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de reservas retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Ponto não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<Reserva> getReservasByPontoId(@PathVariable Long id) {
        return reservaService.findAllByPontoId(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Cria uma nova reserva.", description = "Endpoint para criar uma nova reserva")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Reserva cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação da reserva"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Reserva createReserva(@RequestBody Reserva reserva) {
        return reservaService.create(reserva);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma reserva existente.", description = "Endpoint para atualizar uma reserva existente com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reserva atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Reserva não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @RequestBody Reserva reservaDetails) {
        Reserva updatedReserva = reservaService.update(id, reservaDetails);
        return ResponseEntity.ok(updatedReserva);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Deleta uma reserva pelo ID.", description = "Endpoint que deleta uma reserva com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Reserva deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Reserva não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
package br.com.example.ecocharge.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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
import org.springframework.web.multipart.MultipartFile;

import br.com.example.ecocharge.dto.UsuarioResponse;
import br.com.example.ecocharge.model.Usuario;
import br.com.example.ecocharge.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "usuarios", description = "Endpoint relacionado com usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Lista todos os usuários cadastrados no sistema.", description = "Endpoint que retorna uma lista de objetos do tipo usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public List<UsuarioResponse> index() {
        List<Usuario> usuarios = usuarioService.findAll();
        return usuarios.stream().map(UsuarioResponse::from).toList();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Cria um novo usuário.", description = "Endpoint para criar um novo usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação do usuário"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.create(usuario);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um usuário específico cadastrado no sistema.", description = "Endpoint que retorna um objeto do tipo usuário com um id informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<UsuarioResponse> getUsuarioById(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(UsuarioResponse.from(usuario));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("perfil/{id}")
    @Operation(summary = "Retorna uma imagem de perfil pelo ID do usuário.", description = "Endpoint que retorna uma imagem de perfil com um ID de usuário informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Imagem de perfil retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Imagem de perfil não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Resource> getImageById(@PathVariable Long id) throws IOException {
        return usuarioService.getImageById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um usuário existente.", description = "Endpoint para atualizar um usuário existente com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        try {
            return ResponseEntity.ok(usuarioService.update(id, usuarioDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Deleta um usuário pelo ID.", description = "Endpoint que deleta um usuário com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/perfil/{id}")
    @Operation(summary = "Faz upload de uma imagem de perfil para um usuário.", description = "Endpoint para fazer upload de uma imagem de perfil para um usuário com um ID informado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Imagem de perfil carregada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro ao carregar a imagem de perfil"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> uploadImg(@PathVariable Long id, @RequestBody MultipartFile file) {
        try {
            String response = usuarioService.uploadImage(id, file);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

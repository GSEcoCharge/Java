package br.com.example.ecocharge.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import br.com.example.ecocharge.model.Usuario;
import br.com.example.ecocharge.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> index() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.create(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        try {
            return ResponseEntity.ok(usuarioService.update(id, usuarioDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<String> uploadImg(@PathVariable Long id, @RequestBody MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("O arquivo está vazio");
        }
        Path path = Path.of("src/main/resources/static/files/");
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao criar o diretório", e);
            }
        }
        Path destinationFile = path.resolve(Paths.get(System.currentTimeMillis() + file.getOriginalFilename()))
                .normalize().toAbsolutePath();

        try (InputStream is = file.getInputStream()) {
            Files.copy(is, destinationFile);
            System.out.println("Arquivo copiado");

            var imgURL = destinationFile.getFileName().toString();
            Usuario usuario = usuarioService.findById(id);
            usuario.setImg_perfil(imgURL);
            System.out.println("Update Controller: " + usuario);
            usuarioService.update(id, usuario);

            return ResponseEntity.ok("Imagem de perfil salva");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar o arquivo", e);
        }
    }
}

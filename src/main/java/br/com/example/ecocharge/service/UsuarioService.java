package br.com.example.ecocharge.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.example.ecocharge.model.Usuario;
import br.com.example.ecocharge.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi encontrado o usuário com o id: " + id));
    }

    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, Usuario usuarioDetails) {
        Usuario usuario = findById(id);
        usuario.setNome(usuarioDetails.getNome());
        usuario.setEmail(usuarioDetails.getEmail());
        usuario.setSenha(usuarioDetails.getSenha());
        usuario.setPerfil(usuarioDetails.getPerfil());
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public String uploadImage(Long id, MultipartFile file) {
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
            Usuario usuario = findById(id);
            usuario.setPerfil(imgURL);
            update(id, usuario);

            return "Imagem de perfil salva";
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o arquivo");
        }
    }

    public ResponseEntity<Resource> getImage(String fileName) throws IOException {
        Path path = Paths.get("src/main/resources/static/files/" + fileName);
        Resource resource = new UrlResource(path.toUri());
        
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    
}
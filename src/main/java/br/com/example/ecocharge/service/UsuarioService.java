package br.com.example.ecocharge.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import br.com.example.ecocharge.model.Reserva;
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
        return usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Não foi encontrado o usuário com o id: " + id));
    }

    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario create(OAuth2User principal) {
        var usuario = new Usuario();
        usuario.setNome(principal.getAttribute("name"));
        usuario.setEmail(principal.getAttribute("email"));
        usuario.setPerfil(principal.getAttribute("picture"));
        usuario.setUltima_localizacao(principal.getAttribute("locale"));
        return usuarioRepository.save(usuario);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    public Usuario update(Long id, Usuario usuario) {
        verificarId(id);
        usuario.setId(id);
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

    public ResponseEntity<Resource> getImageByName(String fileName) throws IOException {
        Path path = Paths.get("src/main/resources/static/files/" + fileName);
        Resource resource = new UrlResource(path.toUri());
        
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    public ResponseEntity<Resource> getImageById(Long id) throws MalformedURLException {
        Usuario usuario = findById(id);
        String fileName = usuario.getPerfil();
        Path path = Paths.get("src/main/resources/static/files/" + fileName);
        Resource resource = new UrlResource(path.toUri());
        
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }    

    public void verificarId(Long id){
        usuarioRepository.
        findById(id)
        .orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "id não encontrado")
        );
    }
}
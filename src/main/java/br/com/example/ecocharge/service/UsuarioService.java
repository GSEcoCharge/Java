package br.com.example.ecocharge.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import br.com.example.ecocharge.chat.ChatService;
import br.com.example.ecocharge.mail.EmailService;
import br.com.example.ecocharge.model.Usuario;
import br.com.example.ecocharge.repository.UsuarioRepository;

@Service
public class UsuarioService extends DefaultOAuth2UserService{
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private final UsuarioRepository usuarioRepository;
    private final ChatService chatService;
    private final EmailService emailService;

    public UsuarioService(UsuarioRepository usuarioRepository, ChatService chatService, EmailService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.chatService = chatService;
        this.emailService = emailService;
    }

    public Usuario create(OAuth2User principal) {
        if (usuarioRepository.findByEmail(principal.getAttribute("email")).isEmpty()){
            return usuarioRepository.save(new Usuario(principal));
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Atendente já cadastrado");
    }

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        var oauth2User = super.loadUser(userRequest);
        String email = oauth2User.getAttribute("email");
        return usuarioRepository.findByEmail(email).orElseGet(
            () -> {
                var usuario = new Usuario(oauth2User);
                return usuarioRepository.save(usuario);
            }
        );
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Não foi encontrado o usuário com o id: " + id));
    }

    public Usuario create(Usuario usuario) {
        // String script = chatService.sentToAi(usuario);
        // emailService.sendEmail(usuario.getEmail(), "Bem-vindo ao EcoCharge!", script);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario update(Long id, Usuario usuario) {
        verificarId(id);
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public String uploadImageGoogle(String url) throws IOException {
        Path path = Paths.get("src/main/resources/static/files/");
        Path destinationFile = path.resolve(Paths.get(System.currentTimeMillis() + ".jpg"))
                .normalize().toAbsolutePath();
        try (InputStream inputStream = new URL(url).openStream()) {
            Files.copy(inputStream, destinationFile);
            return destinationFile.getFileName().toString();
        }catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o arquivo");
        }
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
package br.com.example.ecocharge;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import br.com.example.ecocharge.model.Usuario;
import br.com.example.ecocharge.repository.UsuarioRepository;
import br.com.example.ecocharge.service.UsuarioService;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Usuario> usuarios = List.of(new Usuario(), new Usuario());
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> result = usuarioService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testFindById() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.findById(1L);

        assertNotNull(result);
    }

    @Test
    void testFindByEmail() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = usuarioService.findByEmail("test@example.com");

        assertTrue(result.isPresent());
    }

    @Test
    void testDeleteById() {
        doNothing().when(usuarioRepository).deleteById(1L);

        usuarioService.deleteById(1L);

        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void testUploadImage() throws Exception {
        MultipartFile file = mock(MultipartFile.class);
        when(file.isEmpty()).thenReturn(false);
        when(file.getOriginalFilename()).thenReturn("image.jpg");
        InputStream inputStream = mock(InputStream.class);
        when(file.getInputStream()).thenReturn(inputStream);
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        String result = usuarioService.uploadImage(1L, file);

        assertEquals("Imagem de perfil salva", result);
    }

    @Test
    void testGetImageById() throws MalformedURLException {
        Usuario usuario = new Usuario();
        usuario.setPerfil("image.jpg");
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Path path = Paths.get("src/main/resources/static/files/image.jpg");
        Resource resource = new UrlResource(path.toUri());

        ResponseEntity<Resource> result = usuarioService.getImageById(1L);

        assertNotNull(result);
        assertEquals(resource.getFilename(), result.getBody().getFilename());
    }

    @Test
    void testVerificarId() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(new Usuario()));

        usuarioService.verificarId(1L);

        verify(usuarioRepository).findById(1L);
    }
}
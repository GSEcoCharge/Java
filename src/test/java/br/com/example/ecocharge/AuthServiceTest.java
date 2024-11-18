package br.com.example.ecocharge;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import br.com.example.ecocharge.auth.AuthService;
import br.com.example.ecocharge.auth.Credentials;
import br.com.example.ecocharge.auth.Token;
import br.com.example.ecocharge.auth.TokenService;
import br.com.example.ecocharge.mail.EmailService;
import br.com.example.ecocharge.model.Usuario;
import br.com.example.ecocharge.service.UsuarioService;

public class AuthServiceTest {

    private AuthService authService;
    private UsuarioService usuarioService;
    private PasswordEncoder passwordEncoder;
    private EmailService emailService;
    private TokenService tokenService;

    @BeforeEach
    public void setUp() {
        usuarioService = mock(UsuarioService.class);
        passwordEncoder = new BCryptPasswordEncoder();
        emailService = mock(EmailService.class);
        tokenService = mock(TokenService.class);
        authService = new AuthService(tokenService, usuarioService, passwordEncoder, emailService);
    }

    @Test
    public void testValidLogin() {
        String email = "ana.silva@example.com";
        String password = "ana";
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(password));

        when(usuarioService.findByEmail(email)).thenReturn(Optional.of(usuario));
        when(tokenService.createToken(email)).thenReturn(new Token("token", email));

        Credentials credentials = new Credentials(email, password);
        Token token = authService.login(credentials);

        assertTrue(token.token().equals("token"));
        verify(emailService).sendEmail(email, "Login", "Usuário logado com sucesso");
    }

    @Test
    public void testInvalidLogin() {
        String email = "invalidUser@example.com";
        String password = "invalidPassword";

        when(usuarioService.findByEmail(email)).thenReturn(Optional.empty());

        Credentials credentials = new Credentials(email, password);
        assertThrows(ResponseStatusException.class, () -> authService.login(credentials));
    }

    
}
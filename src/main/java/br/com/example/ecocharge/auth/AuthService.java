package br.com.example.ecocharge.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.example.ecocharge.service.UsuarioService;

@Service
public class AuthService {
    private final TokenService tokenService;
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(TokenService tokenService, UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    public Token login(Credentials credentials) {
        var usuario = usuarioService.findByEmail(credentials.email());
        if (usuario == null || !passwordEncoder.matches(credentials.senha(), usuario.getSenha())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário ou senha inválidos");
        }
        return tokenService.createToken(credentials.email());
    }

    // public Token loginOAuth2(OAuth2User principal){
    //     var usuario = usuarioService.findByEmail(principal.getAttribute("email"));
    //     if(usuario == null){
    //         usuario = new Usuario(principal);
    //     }
    //     return tokenService.createToken(usuario.getEmail());
    // }


}

package br.com.example.ecocharge.auth;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.example.ecocharge.model.Usuario;
import br.com.example.ecocharge.service.UsuarioService;

@Service
public class TokenService {

    private static final Algorithm ALGORITHM = Algorithm.HMAC256("ecocharge");
    private final UsuarioService usuarioService;

    public TokenService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Token createToken(String email){
        var expirationAt = LocalDateTime.now().plus(1, ChronoUnit.HOURS).toInstant(ZoneOffset.ofHours(-3));

        String token = JWT.create()
            .withSubject(email)
            .withExpiresAt(expirationAt)
            .withIssuer("ecocharge")
            .sign(ALGORITHM);
        return new Token(token, email);
    }

    public Usuario getUsuarioFromToken(String token) {
        var email = JWT.require(ALGORITHM)
                .withIssuer("ecocharge")
                .build()
                .verify(token)
                .getSubject();    
        var usuario = usuarioService.findByEmail(email);
        if (usuario != null) {
            return usuario;
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }

}

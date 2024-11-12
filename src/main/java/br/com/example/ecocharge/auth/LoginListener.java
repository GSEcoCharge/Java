package br.com.example.ecocharge.auth;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import br.com.example.ecocharge.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final UsuarioService usuarioService;

    public LoginListener(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        var principal = (OAuth2User) event.getAuthentication().getPrincipal();
        usuarioService.create(principal);
    }

}

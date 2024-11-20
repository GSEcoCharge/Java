package br.com.example.ecocharge.auth;

import java.io.IOException;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import br.com.example.ecocharge.model.Usuario;
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
        Object principal = (OAuth2User) event.getAuthentication().getPrincipal();
        if (principal instanceof OAuth2User){
            OAuth2User oauthUser = (OAuth2User) principal;
            String email = oauthUser.getAttribute("email");
            String name = oauthUser.getAttribute("name");
            String perfil = oauthUser.getAttribute("picture");
            usuarioService.findByEmail(email).orElseGet(
                () -> {
                    Usuario usuario = new Usuario();
                    usuario.setEmail(email);
                    usuario.setNome(name);
                    try {
                        usuario.setPerfil(usuarioService.uploadImageGoogle(perfil));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    usuario.setSenha(usuario.getEmail());
                    return usuarioService.create(usuario);
                });
        }
    }

}

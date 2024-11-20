package br.com.example.ecocharge.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "GS_USUARIO")
public class Usuario extends DefaultOAuth2User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario")
    @SequenceGenerator(name = "usuario", sequenceName = "GS_USUARIO_SEQ", allocationSize = 1)
    @Column(name = "USUARIO_ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "IMG_PERFIL")
    private String perfil;

    @Column(name = "DATA_CRIACAO")
    private LocalDateTime criacao;
    
    @Column(name = "ULTIMA_LOCALIZACAO")
    private String localizacao;

    @PrePersist
    protected void onCreate() {
        criacao = LocalDateTime.now().withSecond(0).withNano(0);
    }

    public Usuario() {
        super(List.of(), Map.of("name", "Anonymous", "email", "anonymous@example.com"), "name");
        this.nome = "Anonymous";
        this.email = "anonymous@example.com";
        this.perfil = "default.jpg";
    }

    public Usuario(OAuth2User principal){
        super(List.of(new SimpleGrantedAuthority("USER")), principal.getAttributes(), "email");
        this.nome = principal.getAttribute("name");
        this.email = principal.getAttribute("email");
        this.perfil = principal.getAttribute("perfil");
    }

}

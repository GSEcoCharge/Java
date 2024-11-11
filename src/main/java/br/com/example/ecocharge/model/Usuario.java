package br.com.example.ecocharge.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long usuario_id;

    private String nome;

    private String email;

    private String senha;

    private String img_perfil;

    private Date data_criacao;
    
    private String ultima_localizacao;

    @PrePersist
    protected void onCreate() {
        data_criacao = new Date();
    }
}

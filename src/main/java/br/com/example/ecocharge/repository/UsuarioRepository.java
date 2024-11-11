package br.com.example.ecocharge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.ecocharge.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,  Long> {


}

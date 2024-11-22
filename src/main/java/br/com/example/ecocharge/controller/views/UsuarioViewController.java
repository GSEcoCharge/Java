package br.com.example.ecocharge.controller.views;

import br.com.example.ecocharge.model.Usuario;
import br.com.example.ecocharge.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioViewController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/view")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.findAll();
        model.addAttribute("usuarios", usuarios);
        return "usuarios/usuarios";
    }

    @GetMapping("/create")
    public String criarUsuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/usuario-form";
    }

    @PostMapping("/save")
    public String salvarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.create(usuario);
        return "redirect:/usuarios/view";
    }

    @GetMapping("/edit/{id}")
    public String editarUsuarioForm(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.findById(id);
        model.addAttribute("usuario", usuario);
        return "usuarios/usuario-form";
    }

    @GetMapping("/delete/{id}")
    public String deletarUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return "redirect:/usuarios/view";
    }
}

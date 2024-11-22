package br.com.example.ecocharge.controller.views;

import br.com.example.ecocharge.model.Viagem;
import br.com.example.ecocharge.service.UsuarioService;
import br.com.example.ecocharge.service.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/viagens")
public class ViagemViewController {

    @Autowired
    private ViagemService viagemService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/view")
    public String listarViagens(Model model) {
        List<Viagem> viagens = viagemService.findAll();
        model.addAttribute("viagens", viagens);
        return "viagens/viagens";
    }

    @GetMapping("/create")
    public String criarViagemForm(Model model) {
        model.addAttribute("viagem", new Viagem());
        model.addAttribute("usuarios", usuarioService.findAll());
        return "viagens/viagem-form";
    }

    @PostMapping("/save")
    public String salvarViagem(@ModelAttribute("viagem") Viagem viagem) {
        viagemService.create(viagem);
        return "redirect:/viagens/view";
    }

    @GetMapping("/edit/{id}")
    public String editarViagemForm(@PathVariable Long id, Model model) {
        Viagem viagem = viagemService.findById(id);
        model.addAttribute("viagem", viagem);
        model.addAttribute("usuarios", usuarioService.findAll());
        return "viagens/viagem-form";
    }

    @GetMapping("/delete/{id}")
    public String deletarViagem(@PathVariable Long id) {
        viagemService.deleteById(id);
        return "redirect:/viagens/view";
    }
}

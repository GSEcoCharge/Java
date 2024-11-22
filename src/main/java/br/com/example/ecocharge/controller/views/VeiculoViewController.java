package br.com.example.ecocharge.controller.views;

import br.com.example.ecocharge.model.Veiculo;
import br.com.example.ecocharge.service.UsuarioService;
import br.com.example.ecocharge.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/veiculos")
public class VeiculoViewController {

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/view")
    public String listarVeiculos(Model model) {
        model.addAttribute("veiculos", veiculoService.findAll());
        return "veiculos/veiculos";
    }

    @GetMapping("/create")
    public String criarVeiculoForm(Model model) {
        model.addAttribute("veiculo", new Veiculo());
        model.addAttribute("usuarios", usuarioService.findAll()); // Fetch all users
        return "veiculos/veiculo-form";
    }

    @PostMapping("/save")
    public String salvarVeiculo(@ModelAttribute("veiculo") Veiculo veiculo) {
        veiculoService.create(veiculo);
        return "redirect:/veiculos/view";
    }

    @GetMapping("/edit/{id}")
    public String editarVeiculoForm(@PathVariable Long id, Model model) {
        Veiculo veiculo = veiculoService.findById(id);
        model.addAttribute("veiculo", veiculo);
        model.addAttribute("usuarios", usuarioService.findAll()); // Fetch all users
        return "veiculos/veiculo-form";
    }

    @GetMapping("/delete/{id}")
    public String deletarVeiculo(@PathVariable Long id) {
        veiculoService.deleteById(id);
        return "redirect:/veiculos/view";
    }
}

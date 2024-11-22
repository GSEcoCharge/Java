package br.com.example.ecocharge.controller.views;

import br.com.example.ecocharge.model.PostoCarregamento;
import br.com.example.ecocharge.service.PostoCarregamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/postos")
public class PostoCarregamentoViewController {

    @Autowired
    private PostoCarregamentoService postoService;

    @GetMapping("/view")
    public String listarPostos(Model model) {
        List<PostoCarregamento> postos = postoService.findAll();
        model.addAttribute("postos", postos);
        return "postos/postos";
    }

    @GetMapping("/create")
    public String criarPostoForm(Model model) {
        model.addAttribute("posto", new PostoCarregamento());
        return "postos/posto-form";
    }

    @PostMapping("/save")
    public String salvarPosto(@ModelAttribute("posto") PostoCarregamento posto) {
        postoService.create(posto);
        return "redirect:/postos/view";
    }

    @GetMapping("/edit/{id}")
    public String editarPostoForm(@PathVariable Long id, Model model) {
        PostoCarregamento posto = postoService.findById(id);
        model.addAttribute("posto", posto);
        return "postos/posto-form";
    }

    @GetMapping("/delete/{id}")
    public String deletarPosto(@PathVariable Long id) {
        postoService.deleteById(id);
        return "redirect:/postos/view";
    }
}

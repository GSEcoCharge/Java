package br.com.example.ecocharge.controller.views;

import br.com.example.ecocharge.model.PontoCarregamento;
import br.com.example.ecocharge.service.PontoCarregamentoService;
import br.com.example.ecocharge.service.PostoCarregamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pontos")
public class PontoCarregamentoViewController {

    @Autowired
    private PontoCarregamentoService pontoService;

    @Autowired
    private PostoCarregamentoService postoService;

    @GetMapping("/view")
    public String listarPontos(Model model) {
        List<PontoCarregamento> pontos = pontoService.findAll();
        model.addAttribute("pontos", pontos);
        return "pontos/pontos";
    }

    @GetMapping("/create")
    public String criarPontoForm(Model model) {
        model.addAttribute("ponto", new PontoCarregamento());
        model.addAttribute("postos", postoService.findAll());
        return "pontos/ponto-form";
    }

    @PostMapping("/save")
    public String salvarPonto(@ModelAttribute("ponto") PontoCarregamento ponto) {
        pontoService.create(ponto);
        return "redirect:/pontos/view";
    }

    @GetMapping("/edit/{id}")
    public String editarPontoForm(@PathVariable Long id, Model model) {
        PontoCarregamento ponto = pontoService.findById(id);
        model.addAttribute("ponto", ponto);
        model.addAttribute("postos", postoService.findAll());
        return "pontos/ponto-form";
    }

    @GetMapping("/delete/{id}")
    public String deletarPonto(@PathVariable Long id) {
        pontoService.deleteById(id);
        return "redirect:/pontos/view";
    }
}

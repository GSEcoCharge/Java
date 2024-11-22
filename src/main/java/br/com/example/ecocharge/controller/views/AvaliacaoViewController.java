package br.com.example.ecocharge.controller.views;

import br.com.example.ecocharge.model.Avaliacao;
import br.com.example.ecocharge.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/avaliacoes")
public class AvaliacaoViewController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping("/view")
    public String listarAvaliacoes(Model model, Pageable pageable) {
        Page<Avaliacao> avaliacoes = avaliacaoService.findAll(pageable);
        model.addAttribute("avaliacoes", avaliacoes);
        return "avaliacoes/avaliacoes";
    }

    @GetMapping("/create")
    public String criarAvaliacaoForm(Model model) {
        model.addAttribute("avaliacao", new Avaliacao());
        return "avaliacoes/avaliacao-form";
    }

    @PostMapping("/save")
    public String salvarAvaliacao(@ModelAttribute("avaliacao") Avaliacao avaliacao) {
        avaliacaoService.create(avaliacao);
        return "redirect:/avaliacoes/view";
    }

    @GetMapping("/edit/{id}")
    public String editarAvaliacaoForm(@PathVariable Long id, Model model) {
        Avaliacao avaliacao = avaliacaoService.findById(id);
        model.addAttribute("avaliacao", avaliacao);
        return "avaliacoes/avaliacao-form";
    }

    @GetMapping("/delete/{id}")
    public String deletarAvaliacao(@PathVariable Long id) {
        avaliacaoService.deleteById(id);
        return "redirect:/avaliacoes/view";
    }
}

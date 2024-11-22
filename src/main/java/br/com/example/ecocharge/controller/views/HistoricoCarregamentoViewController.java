package br.com.example.ecocharge.controller.views;

import br.com.example.ecocharge.model.HistoricoCarregamento;
import br.com.example.ecocharge.service.HistoricoCarregamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/historicos")
public class HistoricoCarregamentoViewController {

    @Autowired
    private HistoricoCarregamentoService historicoCarregamentoService;

    @GetMapping("/view")
    public String listarHistoricos(Model model) {
        List<HistoricoCarregamento> historicos = historicoCarregamentoService.findAll();
        model.addAttribute("historicos", historicos);
        return "historicos/historicos";
    }

    @GetMapping("/create")
    public String criarHistoricoForm(Model model) {
        model.addAttribute("historico", new HistoricoCarregamento());
        return "historicos/historico-form";
    }

    @PostMapping("/save")
    public String salvarHistorico(@ModelAttribute("historico") HistoricoCarregamento historico) {
        historicoCarregamentoService.create(historico);
        return "redirect:/historicos/view";
    }

    @GetMapping("/edit/{id}")
    public String editarHistoricoForm(@PathVariable Long id, Model model) {
        HistoricoCarregamento historico = historicoCarregamentoService.findById(id);
        model.addAttribute("historico", historico);
        return "historicos/historico-form";
    }

    @GetMapping("/delete/{id}")
    public String deletarHistorico(@PathVariable Long id) {
        historicoCarregamentoService.deleteById(id);
        return "redirect:/historicos/view";
    }
}

package br.com.example.ecocharge.controller.views;

import br.com.example.ecocharge.model.PontoParada;
import br.com.example.ecocharge.service.PontoParadaService;
import br.com.example.ecocharge.service.PontoCarregamentoService;
import br.com.example.ecocharge.service.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pontos-parada")
public class PontoParadaViewController {

    @Autowired
    private PontoParadaService pontoParadaService;

    @Autowired
    private ViagemService viagemService;

    @Autowired
    private PontoCarregamentoService pontoCarregamentoService;

    @GetMapping("/view")
    public String listarPontosParada(Model model) {
        List<PontoParada> pontosParada = pontoParadaService.findAll();
        model.addAttribute("pontosParada", pontosParada);
        return "pontos-parada/pontos-parada";
    }

    @GetMapping("/create")
    public String criarPontoParadaForm(Model model) {
        model.addAttribute("pontoParada", new PontoParada());
        model.addAttribute("viagens", viagemService.findAll());
        model.addAttribute("pontosCarregamento", pontoCarregamentoService.findAll());
        return "pontos-parada/ponto-parada-form";
    }

    @PostMapping("/save")
    public String salvarPontoParada(@ModelAttribute("pontoParada") PontoParada pontoParada) {
        pontoParadaService.create(pontoParada);
        return "redirect:/pontos-parada/view";
    }

    @GetMapping("/edit/{id}")
    public String editarPontoParadaForm(@PathVariable Long id, Model model) {
        PontoParada pontoParada = pontoParadaService.findById(id);
        model.addAttribute("pontoParada", pontoParada);
        model.addAttribute("viagens", viagemService.findAll());
        model.addAttribute("pontosCarregamento", pontoCarregamentoService.findAll());
        return "pontos-parada/ponto-parada-form";
    }

    @GetMapping("/delete/{id}")
    public String deletarPontoParada(@PathVariable Long id) {
        pontoParadaService.deleteById(id);
        return "redirect:/pontos-parada/view";
    }
}

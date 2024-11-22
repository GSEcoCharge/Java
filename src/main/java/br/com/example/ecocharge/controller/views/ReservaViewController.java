package br.com.example.ecocharge.controller.views;

import br.com.example.ecocharge.model.Reserva;
import br.com.example.ecocharge.service.ReservaService;
import br.com.example.ecocharge.service.PontoCarregamentoService;
import br.com.example.ecocharge.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservaViewController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PontoCarregamentoService pontoCarregamentoService;

    @GetMapping("/view")
    public String listarReservas(Model model) {
        List<Reserva> reservas = reservaService.findAll();
        model.addAttribute("reservas", reservas);
        return "reservas/reservas";
    }

    @GetMapping("/create")
    public String criarReservaForm(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("pontos", pontoCarregamentoService.findAll());
        return "reservas/reserva-form";
    }

    @PostMapping("/save")
    public String salvarReserva(@ModelAttribute("reserva") Reserva reserva) {
        reservaService.create(reserva);
        return "redirect:/reservas/view";
    }

    @GetMapping("/edit/{id}")
    public String editarReservaForm(@PathVariable Long id, Model model) {
        Reserva reserva = reservaService.findById(id);
        model.addAttribute("reserva", reserva);
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("pontos", pontoCarregamentoService.findAll());
        return "reservas/reserva-form";
    }

    @GetMapping("/delete/{id}")
    public String deletarReserva(@PathVariable Long id) {
        reservaService.deleteById(id);
        return "redirect:/reservas/view";
    }
}

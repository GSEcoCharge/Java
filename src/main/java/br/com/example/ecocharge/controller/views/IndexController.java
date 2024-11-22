package br.com.example.ecocharge.controller.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        // Adiciona dados ao modelo, se necessário
        model.addAttribute("title", "EcoCharge - Página Inicial");
        return "index"; // Retorna o template "index.html"
    }
}

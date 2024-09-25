package br.unipar.tasktraker.controllers;

import br.unipar.tasktraker.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

//    @Autowired
//    private UsuarioService usuarioService;
//
//    @GetMapping("/login")
//    public String showLoginPage() {
//        return "login";
//    }
//
//    @PostMapping
//    public String login(@RequestParam String email, @RequestParam String senha, Model model) {
//        boolean isAuthenticated = usuarioService.autenticarUsuario(email, senha);
//
//        if (isAuthenticated) {
//            model.addAttribute("email", email);
//            //return "welcome";
//            return "redirect:/inicio";
//        } else {
//            model.addAttribute("error", "Credenciais inválidas!");
//            return "login";
//        }
//    }
//
//    @GetMapping("/inicio")
//    public String showInicio() {
//        return "inicio";
//    }


}

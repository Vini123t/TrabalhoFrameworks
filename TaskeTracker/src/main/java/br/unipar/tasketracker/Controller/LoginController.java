package br.unipar.tasketracker.Controller;

import br.unipar.tasketracker.Repository.UsuarioRepository;
import br.unipar.tasketracker.Service.UsuarioService;
import br.unipar.tasketracker.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String loginForm(HttpSession session) {
        // Criar ou buscar um usuário fictício para pular o login
        Usuario usuario = usuarioRepository.findById(1) // Suponha que o usuário com ID 1 exista
                .orElseGet(() -> { // Se não existir, cria um novo usuário fictício
                    Usuario novoUsuario = new Usuario();
                    novoUsuario.setNome("Usuário Fictício");
                    novoUsuario.setEmail("ficticio@example.com");
                    novoUsuario.setSenha("12345"); // Isso não será usado realmente
                    return usuarioRepository.save(novoUsuario);
                });

        // Definir o usuário na sessão
        session.setAttribute("usuarioLogado", usuario);

        // Redirecionar diretamente para a tela inicial
        return "redirect:/usuario/telainicial";
    }

}



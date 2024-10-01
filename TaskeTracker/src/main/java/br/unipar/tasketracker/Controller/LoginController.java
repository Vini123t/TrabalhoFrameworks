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

    @GetMapping("/login")
    public String loginForm(HttpSession session) {
        // Aqui você pode criar um usuário fictício e adicioná-lo à sessão para pular o login
        Usuario usuario = new Usuario();
        usuario.setNome("Usuário Fictício");
        usuario.setEmail("ficticio@example.com");
        session.setAttribute("usuarioLogado", usuario);

        // Redireciona diretamente para a tela inicial do usuário
        return "redirect:/usuario/telainicial";
    }
}





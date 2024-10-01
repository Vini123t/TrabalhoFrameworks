package br.unipar.tasketracker.Controller;

import br.unipar.tasketracker.Repository.UsuarioRepository;
import br.unipar.tasketracker.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String home(HttpSession session) {
        // Buscar o usuário direto no banco de dados
        Usuario usuario = usuarioRepository.findByEmail("teste@hotmail.com")
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Armazenar o usuário na sessão
        session.setAttribute("usuarioLogado", usuario);

        // Redirecionar para a tela inicial
        return "redirect:/usuario/telainicial";
    }
}


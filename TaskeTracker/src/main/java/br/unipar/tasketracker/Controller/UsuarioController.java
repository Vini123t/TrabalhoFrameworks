package br.unipar.tasketracker.Controller;

import br.unipar.tasketracker.Service.HabitoService;
import br.unipar.tasketracker.Service.TarefaService;
import br.unipar.tasketracker.model.Habito;
import br.unipar.tasketracker.model.Tarefa;
import br.unipar.tasketracker.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @GetMapping("/telainicial")
    public String telaInicial(HttpSession session, Model model) {
        // Obter o usuário logado da sessão
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            // Redirecionar para o login se o usuário não estiver logado
            return "redirect:/login";
        }

        // Exibir as informações do usuário na tela inicial
        model.addAttribute("usuario", usuario);
        return "telainicial";
    }
}






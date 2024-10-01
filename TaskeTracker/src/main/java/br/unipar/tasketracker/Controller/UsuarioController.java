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

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private HabitoService habitoService;

    @GetMapping("/telainicial")
    public String telaInicial(HttpSession session, Model model) {
        // Verificar se o usuário está logado
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            // Se não houver usuário logado, redirecionar para a página de login
            return "redirect:/login";
        }

        // Buscar tarefas e hábitos do usuário
        List<Tarefa> tarefas = tarefaService.getTarefasPorUsuario(usuario.getId());
        List<Habito> habitos = habitoService.getHabitosPorUsuario(usuario.getId());

        // Passar os dados para o front-end
        model.addAttribute("usuario", usuario);
        model.addAttribute("tarefas", tarefas);
        model.addAttribute("habitos", habitos);

        return "telainicial"; // Nome da view (HTML)
    }
}





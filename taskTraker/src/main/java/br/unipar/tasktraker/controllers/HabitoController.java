package br.unipar.tasktraker.controllers;

import br.unipar.tasktraker.models.Habito;
import br.unipar.tasktraker.models.HabitoHistorico;
import br.unipar.tasktraker.models.Usuario;
import br.unipar.tasktraker.services.HabitoHistoricoService;
import br.unipar.tasktraker.services.HabitoService;
import br.unipar.tasktraker.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class HabitoController {

    @Autowired
    private HabitoService habitoService;

    @Autowired
    private HabitoHistoricoService habitoHistoricoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/formulario/habito")
    public String mostrarFormularioHabito(Model model) {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "habito";
    }

    @PostMapping("/habitos/adicionar")
    public String adicionarHabito(@RequestParam String descricao,
                                  @RequestParam("usuarios") String usuarioNome) {

        Usuario usuario = usuarioService.buscarPorNome(usuarioNome);

        Habito novoHabito = new Habito();
        novoHabito.setDescricao(descricao);
        novoHabito.setUsuario(usuario);

        habitoService.salvarHabito(novoHabito);
        return "redirect:/inicio";
    }

    @GetMapping("/listar/habito")
    public String listarHabitos(Model model) {
        List<Habito> habitos = habitoService.listarHabito();
        model.addAttribute("habitos", habitos);
        return "redirect:/inicio";
    }

    @PostMapping("/habitos/concluir")
    public String concluirHabito(@RequestParam Integer id) {
        Habito habito = habitoService.buscarPorId(id);
        if (habito != null) {
            HabitoHistorico historico = new HabitoHistorico();
            historico.setData(LocalDate.now());
            historico.setHabito(habito);
            habitoHistoricoService.salvarHistorico(historico);
        }
        return "redirect:/inicio";
    }



}

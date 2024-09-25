package br.unipar.tasktraker.controllers;

import br.unipar.tasktraker.models.Tarefa;
import br.unipar.tasktraker.models.Usuario;
import br.unipar.tasktraker.services.TarefaService;
import br.unipar.tasktraker.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/formulario/tarefa")
    public String mostrarFormularioTarefa(Model model) {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "tarefa";
    }

    @PostMapping("/tarefas/adicionar")
    public String adicionarTarefa(@RequestParam String descricao,
                                  @RequestParam("dataInicio") LocalDateTime dataInicio,
                                  @RequestParam("dataLimite") LocalDateTime dataLimite,
                                  @RequestParam("usuarios") String usuarioNome) {

        Usuario usuario = usuarioService.buscarPorNome(usuarioNome);

        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setDescricao(descricao);
        novaTarefa.setDataInicio(dataInicio);
        novaTarefa.setDataLimite(dataLimite);
        novaTarefa.setConcluida(false);
        novaTarefa.setUsuario(usuario);

        tarefaService.salvarTarefa(novaTarefa);

        return "redirect:/inicio";
    }

    @PostMapping("/tarefas/concluir")
    public String concluirTarefa(@RequestParam Integer id) {
        Tarefa tarefa = tarefaService.buscarPorId(id);
        if (tarefa != null) {
            tarefa.setConcluida(true);
            tarefaService.salvarTarefa(tarefa);
        }
        return "redirect:/inicio";
    }



}

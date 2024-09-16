package br.unipar.tasketracker.Controller;

import br.unipar.tasketracker.Service.TarefaService;
import br.unipar.tasketracker.Service.UsuarioService;
import br.unipar.tasketracker.model.Tarefas;
import br.unipar.tasketracker.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/inicio")
    public String showInicio(Model model) {
        List<Tarefas> tarefas = tarefaService.getAllTarefas();
        model.addAttribute("tarefas", tarefas);
        return "tela-inicio";
    }


    @GetMapping("/tarefas")
    public String mostrarFormularioTarefa(Model model) {
        model.addAttribute("tarefa", new Tarefas()); // Tarefa é uma classe de modelo para a tarefa
        return "tarefas"; // Nome do arquivo HTML na pasta templates
    }

    @PostMapping("/adicionar-tarefa")
    public String adicionarTarefa(@RequestParam String descricao,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataLimite,
                                  @RequestParam int idusuario) {
        // Busca o objeto Usuario a partir do ID
        Usuario usuario = usuarioService.findById(idusuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Tarefas novaTarefa = new Tarefas();
        novaTarefa.setDescricao(descricao);
        novaTarefa.setDataInicio(dataInicio);
        novaTarefa.setDataLimite(dataLimite);
        novaTarefa.setConcluida(false);  // Sempre false ao criar
        novaTarefa.setUsuario(usuario);

        // Chama o serviço para adicionar a tarefa
        tarefaService.adicionarTarefa(novaTarefa);

        return "redirect:/inicio"; // Redireciona para a página inicial
    }

    @PostMapping("/atualizar-tarefa")
    public String atualizarTarefa(@RequestParam Integer id, @RequestParam Boolean concluida) {
        Tarefas tarefas = tarefaService.getAllTarefas().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));

        tarefas.setConcluida(concluida);
        tarefaService.adicionarTarefa(tarefas); // Atualiza a tarefa no banco de dados

        return "redirect:/inicio";
    }

}

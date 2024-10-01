import br.unipar.tasketracker.Service.TarefaService;
import br.unipar.tasketracker.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    // Listar todas as tarefas de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public List<Tarefa> listarTarefasPorUsuario(@PathVariable Integer usuarioId) {
        return tarefaService.getTarefasPorUsuario(usuarioId);
    }

    // Criar uma nova tarefa
    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaService.criarTarefa(tarefa);
    }

    // Editar uma tarefa existente
    @PutMapping("/{id}")
    public Tarefa editarTarefa(@PathVariable Integer id, @RequestBody Tarefa tarefaAtualizada) {
        return tarefaService.atualizarTarefa(id, tarefaAtualizada);
    }

    // Marcar tarefa como concluída
    @PutMapping("/{id}/concluir")
    public Tarefa concluirTarefa(@PathVariable Integer id) {
        return tarefaService.marcarComoConcluida(id);
    }

    // Deletar uma tarefa
    @DeleteMapping("/{id}")
    public void deletarTarefa(@PathVariable Integer id) {
        tarefaService.deletarTarefa(id);
    }
}

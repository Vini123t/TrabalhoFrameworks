package br.unipar.tasketracker.Service;

import br.unipar.tasketracker.Repository.TarefaRepository;
import br.unipar.tasketracker.exception.ResourceNotFoundException;
import br.unipar.tasketracker.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    // Listar todas as tarefas de um usuário
    public List<Tarefa> getTarefasPorUsuario(Integer usuarioId) {
        return tarefaRepository.findByUsuarioId(usuarioId);
    }


    // Criar uma nova tarefa
    public Tarefa criarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    // Atualizar uma tarefa existente
    public Tarefa atualizarTarefa(Integer id, Tarefa tarefaAtualizada) {
        Tarefa tarefa = encontrarTarefaPorId(id);

        // Atualizando os campos
        tarefa.setDescricao(tarefaAtualizada.getDescricao());
        tarefa.setDataInicio(tarefaAtualizada.getDataInicio());
        tarefa.setDataLimite(tarefaAtualizada.getDataLimite());
        tarefa.setConcluida(tarefaAtualizada.getConcluida());

        return tarefaRepository.save(tarefa);
    }

    // Marcar uma tarefa como concluída
    public Tarefa marcarComoConcluida(Integer id) {
        Tarefa tarefa = encontrarTarefaPorId(id);
        tarefa.setConcluida(true);

        return tarefaRepository.save(tarefa);
    }

    // Método auxiliar para encontrar tarefa e evitar repetição de código
    private Tarefa encontrarTarefaPorId(Integer id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));
    }


    // Deletar uma tarefa
    public void deletarTarefa(Integer id) {
        tarefaRepository.deleteById(id);
    }
}

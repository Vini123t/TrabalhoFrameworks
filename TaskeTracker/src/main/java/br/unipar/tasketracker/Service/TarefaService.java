package br.unipar.tasketracker.Service;

import br.unipar.tasketracker.Repository.TarefaRepository;
import br.unipar.tasketracker.model.Tarefas;
import br.unipar.tasketracker.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefasRepository;

    public List<Tarefas> getTarefasDoUsuario(Usuario usuario) {
        return tarefasRepository.findTarefasByUsuario(usuario);
    }

    public Tarefas adicionarTarefa(Usuario usuario, String descricao, LocalDateTime dataInicio, LocalDateTime dataLimite) {
        Tarefas tarefa = new Tarefas();
        tarefa.setUsuario(usuario);
        tarefa.setDescricao(descricao);
        tarefa.setData_inicio(dataInicio);
        tarefa.setData_limite(dataLimite);
        tarefa.setConcluida(false);
        return tarefasRepository.save(tarefa);
    }

    public Tarefas alternarConclusaoTarefa(Integer tarefaId) {
        Tarefas tarefa = tarefasRepository.findById(tarefaId).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefa.setConcluida(!tarefa.getConcluida());
        return tarefasRepository.save(tarefa);
    }

    public void deletarTarefa(Integer tarefaId) {
        tarefasRepository.deleteById(tarefaId);
    }
}
package br.unipar.tasktraker.services;

import br.unipar.tasktraker.models.Tarefa;
import br.unipar.tasktraker.models.Usuario;
import br.unipar.tasktraker.repositories.TarefaRepository;
import br.unipar.tasktraker.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public void salvarTarefa(Tarefa tarefa) {
        tarefaRepository.save(tarefa);
    }

    public Tarefa buscarPorId(Integer id) {
        return tarefaRepository.findById(id).orElse(null);
    }

    public List<Tarefa> listarTarefasNaoConcluidas() {
        return tarefaRepository.findByConcluidaFalse();
    }



}

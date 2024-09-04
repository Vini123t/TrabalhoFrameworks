package br.unipar.tasketracker.Service;

import br.unipar.tasketracker.Repository.TarefaRepository;
import br.unipar.tasketracker.model.Tarefas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefas> findAll() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefas> findById(Integer id) {
        return tarefaRepository.findById(id);
    }

    public List<Tarefas> findByUsuarioId(Integer usuarioId) {
        return tarefaRepository.findByUsuarioId(usuarioId);
    }

    public List<Tarefas> findConcluidasByUsuarioId(Integer usuarioId) {
        return tarefaRepository.findByUsuarioIdAndConcluidaTrue(usuarioId);
    }

    public Tarefas save(Tarefas tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public void deleteById(Integer id) {
        tarefaRepository.deleteById(id);
    }
}
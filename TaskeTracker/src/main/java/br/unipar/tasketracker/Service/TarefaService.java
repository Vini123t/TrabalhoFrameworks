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
    private TarefaRepository tarefaRepository;

    public void adicionarTarefa(String descricao, LocalDateTime datainicio, LocalDateTime datalimite,
                     boolean concluida, Usuario idusuario) {
        Tarefas tarefa = new Tarefas();
        tarefa.setDescricao(descricao);
        tarefa.setDataInicio(datainicio);
        tarefa.setDataLimite(datalimite);
        tarefa.setConcluida(concluida);
        tarefa.setUsuario(idusuario);
        tarefaRepository.save(tarefa);
    }

//    public List<Tarefas> getAllTarefas() {
//        return tarefaRepository.findAll();
//    }
//
//    public Optional<Tarefas> findById(Integer id) {
//        return tarefaRepository.findById(id);
//    }
//
//    public List<Tarefas> findByUsuarioId(Integer usuarioId) {
//        return tarefaRepository.findByUsuarioId(usuarioId);
//    }
//
//    public List<Tarefas> findConcluidasByUsuarioId(Integer usuarioId) {
//        return tarefaRepository.findByUsuarioIdAndConcluidaTrue(usuarioId);
//    }
//
//    public void deleteById(Integer id) {
//        tarefaRepository.deleteById(id);
//    }
}
package br.unipar.tasketracker.Repository;

import br.unipar.tasketracker.model.Tarefas;
import br.unipar.tasketracker.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefas, Integer> {
    // Buscar tarefas por ID do usuário
    List<Tarefas> findByUsuarioId(Integer usuarioId);

    // Buscar tarefas concluídas por ID do usuário
    List<Tarefas> findByUsuarioIdAndConcluidaTrue(Integer usuarioId);

    List<Tarefas> findByUsuario(Usuario usuario);

}
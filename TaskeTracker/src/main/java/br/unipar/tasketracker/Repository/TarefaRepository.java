package br.unipar.tasketracker.Repository;

import br.unipar.tasketracker.model.Tarefas;
import br.unipar.tasketracker.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefas, Integer> {
    List<Tarefas> findTarefasByUsuario(Usuario usuario);
}
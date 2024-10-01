package br.unipar.tasketracker.Repository;
import br.unipar.tasketracker.model.Habito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HabitoRepository extends JpaRepository<Habito, Integer> {
    List<Habito> findByUsuarioId(Integer usuarioId);
}


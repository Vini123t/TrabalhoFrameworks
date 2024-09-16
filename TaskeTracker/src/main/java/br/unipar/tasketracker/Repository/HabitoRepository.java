package br.unipar.tasketracker.Repository;
import br.unipar.tasketracker.model.Habitos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HabitoRepository extends JpaRepository<Habitos, Integer> {
    // Buscar hábitos por ID do usuário
    List<Habitos> findByUsuarioId(Integer usuarioId);

    @Query("SELECT h.descricao FROM Habitos h")
    List<String> findAllDescriptions();
}
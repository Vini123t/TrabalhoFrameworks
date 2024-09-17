package br.unipar.tasketracker.Repository;
import br.unipar.tasketracker.model.HabitoHistorico;
import br.unipar.tasketracker.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HabitoHistoricoRepository extends JpaRepository<HabitoHistorico, Integer> {
    // Buscar histórico por ID do hábito
    List<HabitoHistorico> findByHabitoId(Integer habitoId);
    List<HabitoHistorico> findByHabitoUserAndDateBetween(Usuario user, LocalDate startDate, LocalDate endDate);

}
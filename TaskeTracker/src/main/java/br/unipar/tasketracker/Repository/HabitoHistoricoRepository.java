package br.unipar.tasketracker.Repository;
import br.unipar.tasketracker.model.HabitoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitoHistoricoRepository extends JpaRepository<HabitoHistorico, Integer> {
    List<HabitoHistorico> findByHabitoId(Integer habitoId);
}

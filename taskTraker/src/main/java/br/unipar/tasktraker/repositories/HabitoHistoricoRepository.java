package br.unipar.tasktraker.repositories;

import br.unipar.tasktraker.models.HabitoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface HabitoHistoricoRepository extends JpaRepository<HabitoHistorico, Integer> {

}

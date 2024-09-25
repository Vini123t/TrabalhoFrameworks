package br.unipar.tasktraker.repositories;

import br.unipar.tasktraker.models.Habito;
import br.unipar.tasktraker.models.Usuario;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitoRepository extends JpaRepository<Habito, Integer> {
}

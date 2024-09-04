package br.unipar.tasketracker.Repository;

import br.unipar.tasketracker.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Adicione consultas personalizadas aqui, se necessário
}

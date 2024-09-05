package br.unipar.tasketracker.Service;

import br.unipar.tasketracker.Repository.HabitoRepository;
import br.unipar.tasketracker.model.Habitos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitoService {

    @Autowired
    private HabitoRepository habitoRepository;

    public List<Habitos> findAll() {
        return habitoRepository.findAll();
    }

    public Optional<Habitos> findById(Integer id) {
        return habitoRepository.findById(id);
    }

    public List<Habitos> findByUsuarioId(Integer usuarioId) {
        return habitoRepository.findByUsuarioId(usuarioId);
    }

    public Habitos save(Habitos habito) {
        return habitoRepository.save(habito);
    }

    public void deleteById(Integer id) {
        habitoRepository.deleteById(id);
    }
}
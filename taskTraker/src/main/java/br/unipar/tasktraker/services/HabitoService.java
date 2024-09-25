package br.unipar.tasktraker.services;

import br.unipar.tasktraker.models.Habito;
import br.unipar.tasktraker.repositories.HabitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitoService {

    @Autowired
    private HabitoRepository habitoRepository;

    public void salvarHabito(Habito habito) {
        habitoRepository.save(habito);
    }

    public List<Habito> listarHabito() {
        return habitoRepository.findAll();
    }

    public Habito buscarPorId(Integer id) {
        return habitoRepository.findById(id).orElse(null);
    }
}

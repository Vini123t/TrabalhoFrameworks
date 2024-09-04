package br.unipar.tasketracker.Service;

import br.unipar.tasketracker.Repository.HabitoHistoricoRepository;
import br.unipar.tasketracker.model.HabitoHistorico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitoHistoricoService {

    @Autowired
    private HabitoHistoricoRepository habitoHistoricoRepository;

    public List<HabitoHistorico> findAll() {
        return habitoHistoricoRepository.findAll();
    }

    public Optional<HabitoHistorico> findById(Integer id) {
        return habitoHistoricoRepository.findById(id);
    }

    public List<HabitoHistorico> findByHabitoId(Integer habitoId) {
        return habitoHistoricoRepository.findByHabitoId(habitoId);
    }

    public HabitoHistorico save(HabitoHistorico habitoHistorico) {
        return habitoHistoricoRepository.save(habitoHistorico);
    }

    public void deleteById(Integer id) {
        habitoHistoricoRepository.deleteById(id);
    }
}
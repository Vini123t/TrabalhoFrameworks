package br.unipar.tasktraker.services;

import br.unipar.tasktraker.models.HabitoHistorico;
import br.unipar.tasktraker.repositories.HabitoHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HabitoHistoricoService {

    @Autowired
    private HabitoHistoricoRepository habitoHistoricoRepository;

    public void salvarHistorico(HabitoHistorico historico) {
        habitoHistoricoRepository.save(historico);
    }

}

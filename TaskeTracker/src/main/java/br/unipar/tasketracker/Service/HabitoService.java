package br.unipar.tasketracker.Service;

import br.unipar.tasketracker.Repository.HabitoHistoricoRepository;
import br.unipar.tasketracker.Repository.HabitoRepository;
import br.unipar.tasketracker.model.HabitoHistorico;
import br.unipar.tasketracker.model.Habito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HabitoService {

    @Autowired
    private HabitoRepository habitoRepository;

    @Autowired
    private HabitoHistoricoRepository habitoHistoricoRepository;

    // Listar hábitos de um usuário
    public List<Habito> getHabitosPorUsuario(Integer usuarioId) {
        return habitoRepository.findByUsuarioId(usuarioId);
    }

    // Criar um novo hábito
    public Habito criarHabito(Habito habito) {
        return habitoRepository.save(habito);
    }

    // Marcar hábito como feito (adicionar ao histórico)
    public void marcarHabitoComoFeito(Integer habitoId) {
        HabitoHistorico historico = new HabitoHistorico();
        historico.setId(habitoId);
        historico.setData(LocalDate.now());
        habitoHistoricoRepository.save(historico);
    }


    // Listar histórico de um hábito
    public List<HabitoHistorico> getHistoricoPorHabito(Integer habitoId) {
        return habitoHistoricoRepository.findByHabitoId(habitoId);
    }

    // Deletar um hábito
    public void deletarHabito(Integer id) {
        habitoRepository.deleteById(id);
    }
}

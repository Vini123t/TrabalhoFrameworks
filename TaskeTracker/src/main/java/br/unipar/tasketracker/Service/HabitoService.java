package br.unipar.tasketracker.Service;

import br.unipar.tasketracker.Repository.HabitoHistoricoRepository;
import br.unipar.tasketracker.Repository.HabitoRepository;
import br.unipar.tasketracker.model.HabitoHistorico;
import br.unipar.tasketracker.model.Habitos;
import br.unipar.tasketracker.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HabitoService {

    @Autowired
    private HabitoRepository habitosRepository;
    @Autowired
    private HabitoHistoricoRepository habitoHistoricoRepository;

    public List<Habitos> getHabitosDoUsuario(Usuario usuario) {
        return habitosRepository.findHabitosByUsuario(usuario);
    }

    public Habitos adicionarHabito(Usuario usuario, String descricao) {
        Habitos habito = new Habitos();
        habito.setUsuario(usuario);
        habito.setDescricao(descricao);
        return habitosRepository.save(habito);
    }

    public HabitoHistorico marcarHabitoComoFeito(Integer habitoId) {
        Habitos habito = habitosRepository.findById(habitoId).orElseThrow(() -> new RuntimeException("Hábito não encontrado"));
        HabitoHistorico historico = new HabitoHistorico();
        historico.setHabito(habito);
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDate date = dateTime.toLocalDate();
        historico.setData(LocalDateTime.now());
        return habitoHistoricoRepository.save(historico);
    }

    public List<HabitoHistorico> getHistoricoHabito(Habitos habito, LocalDateTime dataInicio, LocalDateTime dataFim) {
        return habitoHistoricoRepository.findByHabitoAndDataBetween(habito, dataInicio, dataFim);
    }

    public Optional<Habitos> getHabitoById(Integer id) {
        Optional<Habitos> habitos =habitosRepository.findById(id);
        return habitos;
    }
}
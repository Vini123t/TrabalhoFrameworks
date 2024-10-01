package br.unipar.tasketracker.Controller;

import br.unipar.tasketracker.Service.HabitoService;
import br.unipar.tasketracker.dto.HabitoHistoricoRequest;
import br.unipar.tasketracker.model.HabitoHistorico;
import br.unipar.tasketracker.model.Habito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitos")
public class HabitoController {

    @Autowired
    private HabitoService habitoService;

    // Listar hábitos de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public List<Habito> listarHabitosPorUsuario(@PathVariable Integer usuarioId) {
        return habitoService.getHabitosPorUsuario(usuarioId);
    }

    // Criar um novo hábito
    @PostMapping
    public Habito criarHabito(@RequestBody Habito habito) {
        return habitoService.criarHabito(habito);
    }

    // Marcar hábito como feito (adicionar ao histórico)
    @PostMapping("/historico")
    public void marcarHabitoComoFeito(@RequestBody HabitoHistoricoRequest request) {
        habitoService.marcarHabitoComoFeito(request.getHabitoId());
    }

    // Listar histórico de um hábito
    @GetMapping("/{habitoId}/historico")
    public List<HabitoHistorico> listarHistoricoHabito(@PathVariable Integer habitoId) {
        return habitoService.getHistoricoPorHabito(habitoId);
    }

    // Deletar um hábito
    @DeleteMapping("/{id}")
    public void deletarHabito(@PathVariable Integer id) {
        habitoService.deletarHabito(id);
    }
}

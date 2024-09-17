package br.unipar.tasketracker.Controller;

import br.unipar.tasketracker.Service.HabitoService;
import br.unipar.tasketracker.Service.UsuarioService;
import br.unipar.tasketracker.dto.HabitoDto;
import br.unipar.tasketracker.model.HabitoHistorico;
import br.unipar.tasketracker.model.Habitos;
import br.unipar.tasketracker.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/habitos")
public class HabitoController {

    @Autowired
    private HabitoService habitoService;
    @Autowired
    private HabitoService habitosService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Habitos> getHabitosDoUsuario(Authentication authentication) {
        Usuario usuario = (Usuario) usuarioService.loadUserByEmail(authentication.getName());
        return habitosService.getHabitosDoUsuario(usuario);
    }

    @PostMapping
    public Habitos adicionarHabito(@RequestBody HabitoDto habitoDto, Authentication authentication) {
        Usuario usuario = (Usuario) usuarioService.loadUserByEmail(authentication.getName());
        return habitosService.adicionarHabito(usuario, habitoDto.getDescricao());
    }

    @PostMapping("/{id}/marcar")
    public HabitoHistorico marcarHabitoComoFeito(@PathVariable Integer id) {
        return habitosService.marcarHabitoComoFeito(id);
    }

    @GetMapping("/{id}/historico")
    public List<HabitoHistorico> getHistoricoHabito(
            @PathVariable Integer id,
            @RequestParam LocalDateTime dataInicio,
            @RequestParam LocalDateTime dataFim) {
        Optional<Habitos> habito = habitosService.getHabitoById(id);
        return habitosService.getHistoricoHabito(habito.orElse(null), dataInicio, dataFim);
    }
}
package br.unipar.tasketracker.Controller;

import br.unipar.tasketracker.Service.TarefaService;
import br.unipar.tasketracker.Service.UsuarioService;
import br.unipar.tasketracker.dto.TarefasDto;
import br.unipar.tasketracker.model.Tarefas;
import br.unipar.tasketracker.model.Usuario;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefasService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Tarefas> getTarefasDoUsuario(Authentication authentication) {
        Usuario usuario = (Usuario) usuarioService.loadUserByEmail(authentication.name());
        return tarefasService.getTarefasDoUsuario(usuario);
    }

    @PostMapping
    public Tarefas adicionarTarefa(@RequestBody TarefasDto tarefaDto, Authentication authentication) {
        Usuario usuario = (Usuario) usuarioService.loadUserByEmail(authentication.name());
        return tarefasService.adicionarTarefa(usuario, tarefaDto.getDescricao(), tarefaDto.getDataInicio(), tarefaDto.getDataLimite());
    }

    @PostMapping("/{id}/toggle")
    public Tarefas alternarConclusaoTarefa(@PathVariable Integer id) {
        return tarefasService.alternarConclusaoTarefa(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarTarefa(@PathVariable Integer id) {
        tarefasService.deletarTarefa(id);
        return ResponseEntity.ok().build();
    }
}

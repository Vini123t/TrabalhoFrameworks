package br.unipar.tasketracker.Controller;

import br.unipar.tasketracker.Service.TarefaService;
import br.unipar.tasketracker.Service.UsuarioService;
import br.unipar.tasketracker.model.Tarefas;
import br.unipar.tasketracker.model.Usuario;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;
    @Autowired
    private UsuarioService userService;

    @GetMapping
    public List<Tarefas> getUserTasks(Authentication authentication) {
        Usuario user = userService.loadUserByUsername(authentication.getName());
        return taskService.getUserTasks(user);
    }

    @PostMapping
    public Task addTask(@RequestBody TaskDto taskDto, Authentication authentication) {
        User user = userService.loadUserByUsername(authentication.getName());
        return taskService.addTask(user, taskDto.getTitle(), taskDto.getDescription());
    }

    @PostMapping("/{id}/toggle")
    public Task toggleTaskCompletion(@PathVariable Long id) {
        return taskService.toggleTaskCompletion(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Tarefas> getAllTarefas() {
        return tarefaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefas> getTarefaById(@PathVariable Integer id) {
        Optional<Tarefas> tarefa = tarefaService.findById(id);
        return tarefa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Tarefas> getTarefasByUsuarioId(@PathVariable Integer usuarioId) {
        return tarefaService.findByUsuarioId(usuarioId);
    }

    @PostMapping
    public Tarefas createTarefa(@RequestBody Tarefas tarefa) {
        return tarefaService.save(tarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefas> updateTarefa(@PathVariable Integer id, @RequestBody Tarefas tarefaDetails) {
        Optional<Tarefas> tarefa = tarefaService.findById(id);
        if (tarefa.isPresent()) {
            tarefaDetails.setId(id);
            Tarefas updatedTarefa = tarefaService.save(tarefaDetails);
            return ResponseEntity.ok(updatedTarefa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Integer id) {
        if (tarefaService.findById(id).isPresent()) {
            tarefaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

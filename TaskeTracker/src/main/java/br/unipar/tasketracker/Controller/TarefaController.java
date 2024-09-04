package br.unipar.tasketracker.Controller;

import br.unipar.tasketracker.Service.TarefaService;
import br.unipar.tasketracker.model.Tarefas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

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

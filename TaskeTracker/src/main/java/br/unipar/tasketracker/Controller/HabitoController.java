package br.unipar.tasketracker.Controller;

import br.unipar.tasketracker.Service.HabitoService;
import br.unipar.tasketracker.model.Habitos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/habitos")
public class HabitoController {

    @Autowired
    private HabitoService habitoService;

    @GetMapping
    public List<Habitos> getAllHabitos() {
        return habitoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitos> getHabitoById(@PathVariable Integer id) {
        Optional<Habitos> habito = habitoService.findById(id);
        return habito.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Habitos> getHabitosByUsuarioId(@PathVariable Integer usuarioId) {
        return habitoService.findByUsuarioId(usuarioId);
    }

    @PostMapping
    public Habitos createHabito(@RequestBody Habitos habito) {
        return habitoService.save(habito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habitos> updateHabito(@PathVariable Integer id, @RequestBody Habitos habitoDetails) {
        Optional<Habitos> habito = habitoService.findById(id);
        if (habito.isPresent()) {
            habitoDetails.setId(id);
            Habitos updatedHabito = habitoService.save(habitoDetails);
            return ResponseEntity.ok(updatedHabito);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabito(@PathVariable Integer id) {
        if (habitoService.findById(id).isPresent()) {
            habitoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
package br.unipar.tasketracker.Controller;

import br.unipar.tasketracker.Service.HabitoHistoricoService;
import br.unipar.tasketracker.model.HabitoHistorico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/habitos-historico")
public class HabitoHistoricoController {

    @Autowired
    private HabitoHistoricoService habitoHistoricoService;

    @GetMapping
    public List<HabitoHistorico> getAllHabitoHistorico() {
        return habitoHistoricoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitoHistorico> getHabitoHistoricoById(@PathVariable Integer id) {
        Optional<HabitoHistorico> habitoHistorico = habitoHistoricoService.findById(id);
        return habitoHistorico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/habito/{habitoId}")
    public List<HabitoHistorico> getHabitoHistoricoByHabitoId(@PathVariable Integer habitoId) {
        return habitoHistoricoService.findByHabitoId(habitoId);
    }

    @PostMapping
    public HabitoHistorico createHabitoHistorico(@RequestBody HabitoHistorico habitoHistorico) {
        return habitoHistoricoService.save(habitoHistorico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitoHistorico> updateHabitoHistorico(@PathVariable Integer id, @RequestBody HabitoHistorico habitoHistoricoDetails) {
        Optional<HabitoHistorico> habitoHistorico = habitoHistoricoService.findById(id);
        if (habitoHistorico.isPresent()) {
            habitoHistoricoDetails.setId(id);
            HabitoHistorico updatedHabitoHistorico = habitoHistoricoService.save(habitoHistoricoDetails);
            return ResponseEntity.ok(updatedHabitoHistorico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitoHistorico(@PathVariable Integer id) {
        if (habitoHistoricoService.findById(id).isPresent()) {
            habitoHistoricoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
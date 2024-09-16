package br.unipar.tasketracker.Controller;

import br.unipar.tasketracker.Service.TarefaService;
import br.unipar.tasketracker.model.Tarefas;
import br.unipar.tasketracker.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping("/tela-inicio")
    public String mostrarTelaInicio(Model model) {
        model.addAttribute("tela-inicio", new Tarefas());
        return "tela-inicio";
    }

    @GetMapping("/tarefas")
    public String mostrarFormularioTarefa(Model model) {
        model.addAttribute("tarefa", new Tarefas()); // Tarefa é uma classe de modelo para a tarefa
        return "tarefas"; // Nome do arquivo HTML na pasta templates
    }

    @PostMapping("/adicionar-tarefa")
    public String adicionarTarefa(@RequestParam String descricao, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datainicio,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datalimite, @RequestParam boolean concluida,
                                  @RequestParam Usuario idusuario) {
        tarefaService.adicionarTarefa(descricao, datainicio, datalimite, concluida, idusuario);
        return "redirect:/"; // Redireciona para a página principal ou para onde desejar
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<Tarefas> updateTarefa(@PathVariable Integer id, @RequestBody Tarefas tarefaDetails) {
//        Optional<Tarefas> tarefa = tarefaService.findById(id);
//        if (tarefa.isPresent()) {
//            tarefaDetails.setId(id);
//            Tarefas updatedTarefa = tarefaService.save(tarefaDetails);
//            return ResponseEntity.ok(updatedTarefa);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteTarefa(@PathVariable Integer id) {
//        if (tarefaService.findById(id).isPresent()) {
//            tarefaService.deleteById(id);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}

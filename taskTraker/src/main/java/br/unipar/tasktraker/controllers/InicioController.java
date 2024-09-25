package br.unipar.tasktraker.controllers;

import br.unipar.tasktraker.models.Habito;
import br.unipar.tasktraker.models.Tarefa;
import br.unipar.tasktraker.services.HabitoService;
import br.unipar.tasktraker.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class InicioController {

    @Autowired
    private TarefaService tarefaService;
    @Autowired
    private HabitoService habitoService;

    @GetMapping("/inicio")
    public String exibirPaginaInicial(Model model) {
        List<Tarefa> tarefas = tarefaService.listarTarefasNaoConcluidas();
        List<Habito> habitos = habitoService.listarHabito();

        model.addAttribute("tarefas", tarefas);
        model.addAttribute("habitos", habitos);

        return "inicio";
    }

}

package com.example.kanban.controller;

import com.example.kanban.model.Tarefa;
import com.example.kanban.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    @Autowired
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    public Map<String, List<Tarefa>> listarTarefasPorColuna() {
        Map<String, List<Tarefa>> tarefasPorColuna = new HashMap<>();
        tarefasPorColuna.put("A Fazer", tarefaService.listarTarefasPorStatus("A Fazer"));
        tarefasPorColuna.put("Em Progresso", tarefaService.listarTarefasPorStatus("Em Progresso"));
        tarefasPorColuna.put("Concluído", tarefaService.listarTarefasPorStatus("Concluído"));
        return tarefasPorColuna;
    }

    @PostMapping
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        return tarefaService.addTarefa(tarefa);
    }

    @PutMapping("/{id}/move")
    public Tarefa moverTarefa(@PathVariable Long id) {
        return tarefaService.moverParaProximaColuna(id);
    }

    @PutMapping("/{id}")
    public Tarefa atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        tarefaAtualizada.setId(id); // Para garantir que estamos atualizando a tarefa correta
        return tarefaService.atualizarTarefa(tarefaAtualizada);
    }

    @DeleteMapping("/{id}")
    public void excluirTarefa(@PathVariable Long id) {
        tarefaService.deletarTarefa(id);
    }
}

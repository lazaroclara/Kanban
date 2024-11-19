package com.example.kanban.service;

import com.example.kanban.model.Tarefa;
import com.example.kanban.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> listarTarefasPorStatus(String status) {
        return tarefaRepository.findByStatus(status);
    }

    public List<Tarefa> buscarTarefa() {
        return tarefaRepository.findAll();
    }

    public Tarefa addTarefa(Tarefa tarefa) {
        tarefa.setStatus("A Fazer");
        return tarefaRepository.save(tarefa);
    }

    public Optional<Tarefa> buscarTarefaPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    public Tarefa moverParaProximaColuna(Long id) {
        Tarefa tarefa = buscarTarefaPorId(id).orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
        if (tarefa.getStatus().equals("A Fazer")) {
            tarefa.setStatus("Em Progresso");
        } else if (tarefa.getStatus().equals("Em Progresso")) {
            tarefa.setStatus("Concluído");
        }
        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizarTarefa(Tarefa tarefaAtualizada) {
        Tarefa tarefa = buscarTarefaPorId(tarefaAtualizada.getId())
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada"));
        tarefa.setTitulo(tarefaAtualizada.getTitulo());
        tarefa.setDescricao(tarefaAtualizada.getDescricao());
        tarefa.setPrioridade(tarefaAtualizada.getPrioridade());
        tarefa.setDataLimite(tarefaAtualizada.getDataLimite());
        return tarefaRepository.save(tarefa);
    }

    public void deletarTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }
}

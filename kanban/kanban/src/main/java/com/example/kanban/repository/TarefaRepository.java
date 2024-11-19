package com.example.kanban.repository;
import com.example.kanban.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByStatus(String status);
}


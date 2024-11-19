package com.example.kanban.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private String status;
    private String prioridade;
    private LocalDateTime dataLimite; // Campo opcional

    public Tarefa() {
        this.dataCriacao = LocalDateTime.now();
        this.status = "A Fazer";
    }

    public Tarefa(String titulo, String descricao, String status, String prioridade, LocalDateTime dataLimite) {
        this();
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.prioridade = prioridade;
        this.dataLimite = dataLimite;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public LocalDateTime getDataLimite() {
        return dataLimite;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public void setDataLimite(LocalDateTime dataLimite) {
        this.dataLimite = dataLimite;
    }
}

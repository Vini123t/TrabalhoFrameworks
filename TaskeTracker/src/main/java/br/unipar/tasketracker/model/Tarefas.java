package br.unipar.tasketracker.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDateTime;

@Entity
public class Tarefas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime dataInicio;

    @Column(nullable = false)
    private LocalDateTime dataLimite;

    @Column(nullable = false)
    private Boolean concluida = false;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // Construtores
    public Tarefas() {
    }

    public Tarefas(String descricao, LocalDateTime dataInicio,
                   LocalDateTime dataLimite, Boolean concluida,
                   Usuario usuario) {
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataLimite = dataLimite;
        this.concluida = concluida;
        this.usuario = usuario;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(LocalDateTime dataLimite) {
        this.dataLimite = dataLimite;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}


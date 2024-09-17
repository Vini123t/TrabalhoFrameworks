package br.unipar.tasketracker.model;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tarefas")
public class Tarefas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;
    private LocalDateTime data_inicio;
    private LocalDateTime data_limite;
    private Boolean concluida;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

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

    public LocalDateTime getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDateTime data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDateTime getData_limite() {
        return data_limite;
    }

    public void setData_limite(LocalDateTime data_limite) {
        this.data_limite = data_limite;
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


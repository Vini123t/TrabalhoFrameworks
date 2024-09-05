package br.unipar.tasketracker.model;

import jakarta.persistence.*;


import java.util.List;

@Entity
public class Habitos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "habito", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HabitoHistorico> historico;

    // Construtores
    public Habitos() {
    }

    public Habitos(String descricao, Usuario usuario) {
        this.descricao = descricao;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<HabitoHistorico> getHistorico() {
        return historico;
    }

    public void setHistorico(List<HabitoHistorico> historico) {
        this.historico = historico;
    }
}
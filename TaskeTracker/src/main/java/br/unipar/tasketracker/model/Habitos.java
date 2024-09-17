package br.unipar.tasketracker.model;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Data
@Entity
@Table(name = "habitos")
public class Habitos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "habito")
    private List<HabitoHistorico> historicos;

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

    public List<HabitoHistorico> getHistoricos() {
        return historicos;
    }

    public void setHistoricos(List<HabitoHistorico> historicos) {
        this.historicos = historicos;
    }
}
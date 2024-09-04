package br.unipar.tasketracker.model;

import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
public class HabitoHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_habito", nullable = false)
    private Habitos habito;

    // Construtores
    public HabitoHistorico() {
    }

    public HabitoHistorico(LocalDate data, Habitos habito) {
        this.data = data;
        this.habito = habito;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Habitos getHabito() {
        return habito;
    }

    public void setHabito(Habitos habito) {
        this.habito = habito;
    }
}



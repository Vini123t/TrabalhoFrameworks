package br.unipar.tasktraker.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "HABITO_HISTORICO")
public class HabitoHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_habito", nullable = false)
    private Habito habito;

    public HabitoHistorico() {
    }

    public Habito getHabito() {
        return habito;
    }

    public void setHabito(Habito habito) {
        this.habito = habito;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

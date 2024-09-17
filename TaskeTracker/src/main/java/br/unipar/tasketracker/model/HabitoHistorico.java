package br.unipar.tasketracker.model;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "habito_historico")
public class HabitoHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "id_habito")
    private Habitos habito;

    // Construtores
    public HabitoHistorico() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Habitos getHabito() {
        return habito;
    }

    public void setHabito(Habitos habito) {
        this.habito = habito;
    }
}



package br.unipar.tasketracker.dto;

import lombok.Data;

import java.time.LocalDateTime;

public class HabitoHistoricoRequest {
    private Integer habitoId;

    // Getters e Setters

    public Integer getHabitoId() {
        return habitoId;
    }

    public void setHabitoId(Integer habitoId) {
        this.habitoId = habitoId;
    }
}


package br.unipar.tasketracker.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HabitoHistoricoDto {
    private Integer id;
    private LocalDateTime data;
    private Integer idHabito;
}

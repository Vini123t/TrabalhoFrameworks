package br.unipar.tasketracker.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class TarefasDto implements Serializable {
    Integer id;
    String descricao;
    LocalDateTime dataInicio;
    LocalDateTime dataLimite;
    Boolean concluida;
}
package br.unipar.tasketracker.dto;

import lombok.Data;

@Data
public class UsuarioLoginDto {
    private String email;
    private String senha;
}


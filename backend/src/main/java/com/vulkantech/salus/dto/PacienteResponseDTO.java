package com.vulkantech.salus.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteResponseDTO {

    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private int idade;
    private String doenca;
}

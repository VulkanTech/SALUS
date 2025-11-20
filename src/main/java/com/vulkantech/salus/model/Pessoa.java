package com.vulkantech.salus.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass

public abstract class Pessoa {
    @Id
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private int idade;
}
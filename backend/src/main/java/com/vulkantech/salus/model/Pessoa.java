package com.vulkantech.salus.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class Pessoa {
    @Id
    private String cpf;

    private String nome;
    private String email;
    private String telefone;
    private int idade;
}
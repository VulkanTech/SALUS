package com.vulkantech.salus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pacientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente extends Pessoa {

    @Id
    private String cpf; // CPF como ID único

    private String nome;
    private int idade;
    private String telefone;
    private String email;
}

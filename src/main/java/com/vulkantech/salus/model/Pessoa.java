package com.vulkantech.salus.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_pessoas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {
    @Id
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private int idade;
}
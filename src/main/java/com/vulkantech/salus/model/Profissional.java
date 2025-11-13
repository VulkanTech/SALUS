package com.vulkantech.salus.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_profissionais")
@Inheritance(strategy = InheritanceType.JOINED)
public class Profissional {
    @Id
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private int idade;
}
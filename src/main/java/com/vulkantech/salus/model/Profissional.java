package com.vulkantech.salus.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "profissional")
public class Profissional {
    @Id
    private String cpf;
    private String nome;
    private String contato;
    private LocalDate dataNascimento;

    public Profissional(String cpf, String nome, String contato, LocalDate dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.contato = contato;
        this.dataNascimento = dataNascimento;
    }

    public Profissional() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}

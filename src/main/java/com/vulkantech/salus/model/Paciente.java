package com.vulkantech.salus.model;

import com.vulkantech.salus.repository.PacienteRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;

@Entity
@Table(name = "paciente")
public class Paciente {
    @Id
    private String cpf;
    private String nome,contato;
    private LocalDate dataNascimento;

    public Paciente(String nome, String cpf, String contato, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.contato = contato;
        this.dataNascimento = dataNascimento;
    }

    public Paciente() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

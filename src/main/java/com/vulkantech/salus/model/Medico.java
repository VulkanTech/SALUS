package com.vulkantech.salus.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "medico")
public class Medico extends Paciente {
    @Id
    private String crm;
    private String especialidade;

    public Medico(String nome, String cpf, String contato, LocalDate dataNascimento, String crm, String especialidade) {
        super(nome, cpf, contato, dataNascimento);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public Medico() {
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}

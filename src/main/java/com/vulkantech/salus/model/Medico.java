package com.vulkantech.salus.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_medicos")
public class Medico extends Profissional {
    private String crm;
    private String especialidade;

    public Medico(String crm, String especialidade) {
        this.crm = crm;
        this.especialidade = especialidade;
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

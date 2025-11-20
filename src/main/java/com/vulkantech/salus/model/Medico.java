package com.vulkantech.salus.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import com.vulkantech.salus.exception.ConflitodeHorarioException;
import com.vulkantech.salus.exception.DadosInvalidosException;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tb_medicos")
public class Medico extends Pessoa {
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

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

}

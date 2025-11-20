package com.vulkantech.salus.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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

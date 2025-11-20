package com.vulkantech.salus.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_medicos")
public class Medico extends Pessoa {
    private String crm;
    private String especialidade;
}

package com.vulkantech.salus.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_medicos")
public class Medico extends Profissional {
    private String crm;
    private String especialidade;
}

package com.vulkantech.salus.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medico_crm", nullable = false)
    private Medico medico;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_cpf", nullable = false)
    private Paciente paciente;

    private LocalDateTime dataHora;

    private boolean cancelar = false;

    public Consulta() { }

    public Consulta (Medico medico, Paciente paciente, LocalDateTime dataHora){
        this.medico = medico;
        this.paciente = paciente;
        this.dataHora = dataHora;
    }

    public void cancelar() {
        this.cancelar = true;
    }

}

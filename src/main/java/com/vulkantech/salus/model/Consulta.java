package com.vulkantech.salus.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Consulta {
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "medico_crm", nullable = false)
    private Medico medico;
    @ManyToOne
    @JoinColumn(name = "paciente_cpf")
    private Paciente paciente;
    private LocalDateTime dataHora;
    private boolean cancelar;

    public Consulta(Medico medico, Paciente paciente, LocalDateTime dataHora) {
        this.medico = medico;
        this.paciente = paciente;
        this.dataHora = dataHora;
        this.cancelar = false;
    }

    public Consulta() {
    }

    public void cancelar() {
        this.cancelar = true;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public boolean isCancelar() {
        return cancelar;
    }

    public void setCancelar(boolean cancelar) {
        this.cancelar = cancelar;
    }


}

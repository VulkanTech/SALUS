package com.vulkantech.salus.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medico_crm", nullable = false)
    private Medico medico;

    @ManyToOne(optional = false)
    @JoinColumn(name = "paciente_cpf", nullable = false)
    private Paciente paciente;

    private LocalDateTime dataHora;

    private boolean cancelar = false;

    public void cancelar() {
        if (this.cancelar) {
            throw new IllegalStateException("Consulta já cancelada!");//o estado do objeto(ja cancelado), não permite a operação
        }
        if (this.dataHora.isBefore(LocalDateTime.now())) { //se a consulta for antes do horario atual, lança erro
            throw new IllegalStateException("Não é possivel cancelar consulta passada!");
        }
        this.cancelar = true;
    }

}

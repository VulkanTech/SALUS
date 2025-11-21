package com.vulkantech.salus.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AgendamentoRequestDTO {
    @NotNull(message = "O ID do médico é obrigatório.")
    private long medicoId;

    @NotNull(message = "O ID do paciente é obrigatório.")
    private long pacienteId;

    @NotNull(message = "A data da consulta é obrigatória.")
    @Future(message = "A data da consulta deve ser no futuro.")
    private LocalDateTime dataHora;
}
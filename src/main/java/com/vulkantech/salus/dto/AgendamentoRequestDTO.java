package com.vulkantech.salus.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendamentoRequestDTO {
    @NotNull(message = "O CPF do médico é obrigatório.")
    private String medicoCpf;

    @NotNull(message = "O CPF do paciente é obrigatório.")
    private String pacienteCpf;

    @NotNull(message = "A data da consulta é obrigatória.")
    @Future(message = "A data da consulta deve ser no futuro.")
    private LocalDateTime dataHora;
}
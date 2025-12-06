package com.vulkantech.salus.dto;

import ch.qos.logback.classic.pattern.ClassOfCallerConverter;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ConsultaResponse {
    String nomeMedico;
    String crm;
    String especialidade;

    String nomePaciente;
    LocalDateTime dataConsulta;
}

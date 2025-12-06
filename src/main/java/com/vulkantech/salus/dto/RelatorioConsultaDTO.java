package com.vulkantech.salus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor

public class RelatorioConsultaDTO {
    private Long id;
    private LocalDateTime dataHora;
    private String medico;
    private String paciente;
}

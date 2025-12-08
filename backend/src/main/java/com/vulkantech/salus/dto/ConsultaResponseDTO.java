package com.vulkantech.salus.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ConsultaResponseDTO {

    private Long id;
    private String medicoCpf;
    private String pacienteCpf;
    private LocalDateTime dataHora;
    private boolean cancelada;
}

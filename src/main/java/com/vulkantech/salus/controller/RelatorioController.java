package com.vulkantech.salus.controller;

import com.vulkantech.salus.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor

public class RelatorioController {
    private final RelatorioService relatorioService;

    @GetMapping("/consultas")
    public ResponseEntity<?> gerarRelatorioConsultas(
            @RequestParam(required = false) Long medicoId,
            @RequestParam(required = false) Long pacienteId
    ) {
        return ResponseEntity.ok(relatorioService.gerarRelatorioConsultas(medicoId, pacienteId));
    }
}



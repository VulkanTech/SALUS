package com.vulkantech.salus.controller;

import com.vulkantech.salus.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService relatorioService;

    @GetMapping("/consultas")
    public ResponseEntity<?> gerarRelatorioConsultas(
            @RequestParam(required = false) String medicoCpf,
            @RequestParam(required = false) String pacienteCpf
    ) {
        return ResponseEntity.ok(relatorioService.gerarRelatorioConsultas(medicoCpf, pacienteCpf));
    }

    // Relatório por médico
    @GetMapping("/consultas/medico/{cpf}")
    public ResponseEntity<?> listarPorMedico(@PathVariable String cpf) {
        return ResponseEntity.ok(relatorioService.listarPorMedico(cpf));
    }

    // Relatório por período
    @GetMapping("/consultas/periodo")
    public ResponseEntity<?> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim
    ) {
        return ResponseEntity.ok(relatorioService.listarPorPeriodo(inicio, fim));
    }
}

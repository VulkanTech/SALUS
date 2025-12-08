package com.vulkantech.salus.controller;

import com.vulkantech.salus.dto.ConsultaRequestDTO;
import com.vulkantech.salus.dto.ConsultaResponseDTO;
import com.vulkantech.salus.model.Consulta;
import com.vulkantech.salus.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/consultas")
@Tag(name = "Consultas", description = "Gerenciamento de consultas médicas")
public class ConsultaController {

    private ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    //POST - Agendamento
    @PostMapping
    @Operation(summary = "Agendar nova consulta")
    public ResponseEntity<ConsultaResponseDTO> agendar(@RequestBody @Valid ConsultaRequestDTO dto) {

        Consulta consulta = consultaService.agendar(
                dto.getMedicoCpf(),
                dto.getPacienteCpf(),
                dto.getDataHora()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(consulta));
    }

    // GET - Retorna todos os itens
    @GetMapping
    @Operation(summary = "Lista todas as consultas")
    public List<ConsultaResponseDTO> listar() {

        return consultaService.listarTodas()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // GET - Busca individual por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar consulta por ID")
    public ConsultaResponseDTO buscar(@PathVariable Long id) {
        return toResponse(consultaService.buscarPorId(id));
    }

    // GET - Busca consultas pelo CPF do médico
    @GetMapping("/medico/{cpf}")
    @Operation(summary = "Busca consultas pelo CPF do médico")
    public List<ConsultaResponseDTO> listarPorMedico(@PathVariable String cpf) {

        return consultaService.listarPorMedico(cpf)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    //PUT
    @PutMapping("/{id}")
    @Operation(summary = "Remarcar consulta")
    public ConsultaResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid ConsultaRequestDTO dto) {

        Consulta consulta = consultaService.atualizar(
                id,
                dto.getMedicoCpf(),
                dto.getPacienteCpf(),
                dto.getDataHora()
        );

        return toResponse(consulta);
    }

    //DELETE
    @DeleteMapping("/{id}")
    @Operation(summary = "Cancelar uma consulta")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        consultaService.cancelarConsulta(id);
        return ResponseEntity.noContent().build();
    }

    //Conversão Entity -> Response
    private ConsultaResponseDTO toResponse(Consulta consulta) {
        ConsultaResponseDTO dto = new ConsultaResponseDTO();
        dto.setId(consulta.getId());
        dto.setMedicoCpf(consulta.getMedico().getCpf());
        dto.setPacienteCpf(consulta.getPaciente().getCpf());
        dto.setDataHora(consulta.getDataHora());
        dto.setCancelada(consulta.isCancelar());
        return dto;
    }
}

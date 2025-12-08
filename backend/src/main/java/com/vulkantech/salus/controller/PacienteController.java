package com.vulkantech.salus.controller;

import com.vulkantech.salus.dto.PacienteRequestDTO;
import com.vulkantech.salus.dto.PacienteResponseDTO;
import com.vulkantech.salus.model.Paciente;
import com.vulkantech.salus.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@Tag(name = "Pacientes", description = "Gerenciamento de pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // CREATE
    @PostMapping
    @Operation(summary = "Cadastrar um novo paciente")
    public ResponseEntity<PacienteResponseDTO> cadastrar(@Valid @RequestBody PacienteRequestDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setCpf(dto.getCpf());
        paciente.setNome(dto.getNome());
        paciente.setEmail(dto.getEmail());
        paciente.setTelefone(dto.getTelefone());
        paciente.setIdade(dto.getIdade());
        paciente.setDoenca(dto.getDoenca());

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(pacienteService.cadastrar(paciente)));
    }

    // READ - listar todos
    @GetMapping
    @Operation(summary = "Listar todos os pacientes")
    public List<PacienteResponseDTO> listar() {

        return pacienteService.listarTodos()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // READ - buscar por CPF
    @GetMapping("/{cpf}")
    @Operation(summary = "Buscar paciente por CPF")
    public PacienteResponseDTO buscar(@PathVariable String cpf) {
        return toResponse(pacienteService.buscarPorCpf(cpf));
    }

    // UPDATE
    @PutMapping("/{cpf}")
    @Operation(summary = "Atualizar dados do paciente por CPF")
    public PacienteResponseDTO atualizar(@PathVariable String cpf, @Valid @RequestBody PacienteRequestDTO dto) {

        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome());
        paciente.setEmail(dto.getEmail());
        paciente.setTelefone(dto.getTelefone());
        paciente.setIdade(dto.getIdade());
        paciente.setDoenca(dto.getDoenca());

        return toResponse(pacienteService.atualizar(cpf, paciente));
    }


    // DELETE
    @DeleteMapping("/{cpf}")
    @Operation(summary = "Deleta paciente por CPF")
    public ResponseEntity<Void> deletarPaciente(@PathVariable String cpf) {
        pacienteService.deletar(cpf);
        return ResponseEntity.noContent().build();

    }

    private PacienteResponseDTO toResponse(Paciente p) {
        PacienteResponseDTO dto = new PacienteResponseDTO();
        dto.setCpf(p.getCpf());
        dto.setNome(p.getNome());
        dto.setEmail(p.getEmail());
        dto.setTelefone(p.getTelefone());
        dto.setIdade(p.getIdade());
        dto.setDoenca(p.getDoenca());
        return dto;
    }
}


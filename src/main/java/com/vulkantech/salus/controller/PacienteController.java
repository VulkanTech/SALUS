package com.vulkantech.salus.controller;

import com.vulkantech.salus.model.Paciente;
import com.vulkantech.salus.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@Tag(name = "Pacientes", description = "Gerenciamento de pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    // CREATE
    @PostMapping
    @Operation(summary = "Cria novo paciente")
    public void addPaciente(@RequestBody Paciente paciente) {
        pacienteService.addPaciente(paciente);
    }

    // READ - listar todos
    @GetMapping
    @Operation(summary = "Lista todos os pacientes")
    public List<Paciente> getPacientes(){
        return pacienteService.getPacientes();
    }

    // READ - buscar por CPF
    @GetMapping("/{cpf}")
    @Operation(summary = "Busca pacientes por CPF")
    public Paciente getPaciente(@PathVariable String cpf){
        return pacienteService.getPaciente(cpf);
    }

    // UPDATE
    @PutMapping("/{cpf}")
    @Operation(summary = "Atualiza paciente por CPF")
    public void updatePaciente(@PathVariable String cpf, @RequestBody Paciente pacienteAtualizado){
        pacienteService.updatePaciente(cpf, pacienteAtualizado);
    }

    // DELETE
    @DeleteMapping("/{cpf}")
    @Operation(summary = "Deleta paciente por CPF")
    public void deletePaciente(@PathVariable String cpf){
        pacienteService.deletePaciente(cpf);
    }
}

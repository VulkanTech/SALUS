package com.vulkantech.salus.controller;

import com.vulkantech.salus.model.Medico;
import com.vulkantech.salus.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
@Tag(name = "Médicos", description = "Gerenciamento de médicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService medicoService;

    // CREATE
    @PostMapping
    @Operation(summary = "Cria novo médico")
    public void addMedico(@RequestBody Medico medico) {
        medicoService.addMedico(medico);
    }

    // READ - listar todos
    @GetMapping
    @Operation(summary = "Lista todos os médicos")
    public List<Medico> getMedicos(){
        return medicoService.getMedicos();
    }

    // READ - buscar por CPF
    @GetMapping("/{cpf}")
    @Operation(summary = "Busca médicos por CPF")
    public Medico getMedico(@PathVariable String cpf){
        return medicoService.getMedico(cpf);
    }

    // UPDATE
    @PutMapping("/{cpf}")
    @Operation(summary = "Atualiza médico por CPF")
    public void updateMedico(@PathVariable String cpf, @RequestBody Medico medicoAtualizado){
        medicoService.updateMedico(cpf, medicoAtualizado);
    }

    // DELETE
    @DeleteMapping("/{cpf}")
    @Operation(summary = "Deleta médico por CPF")
    public void deleteMedico(@PathVariable String cpf){
        medicoService.deleteMedico(cpf);
    }
}

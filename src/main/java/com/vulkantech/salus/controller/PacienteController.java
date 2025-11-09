package com.vulkantech.salus.controller;


import com.vulkantech.salus.model.Paciente;
import com.vulkantech.salus.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    // CREATE
    @PostMapping
    public void addPaciente(@RequestBody Paciente paciente) {
        pacienteService.addPaciente(paciente);
    }

    // READ - listar todos
    @GetMapping
    public List<Paciente> getPacientes(){
        return pacienteService.getPacientes();
    }

    // READ - buscar por CPF
    @GetMapping("/{cpf}")
    public Paciente getPaciente(@PathVariable String cpf){
        return pacienteService.getPaciente(cpf);
    }

    // UPDATE
    @PutMapping("/{cpf}")
    public void updatePaciente(@PathVariable String cpf, @RequestBody Paciente pacienteAtualizado){
        pacienteService.updatePaciente(cpf, pacienteAtualizado);
    }

    //DELETE
    @DeleteMapping("{cpf}")
    public void deletePaciente(@PathVariable String cpf){
        pacienteService.deletePaciente(cpf);
    }

}

package com.vulkantech.salus.service;


import com.vulkantech.salus.model.Paciente;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class PacienteService {

    private List<Paciente> pacientes = new ArrayList<>();

    // CREATE
    public void addPaciente(Paciente paciente) {

        if (paciente.getCpf() == null || paciente.getCpf().isBlank()) {
            throw new RuntimeException("CPF não pode ser nulo ou vazio!");
        }

        // Evita CPFs duplicados
        for (Paciente p : pacientes) {
            if (p.getCpf().equals(paciente.getCpf())) {
                throw new RuntimeException("CPF já cadastrado!");
            }
        }
        pacientes.add(paciente);
    }

    // READ
    public Paciente getPaciente(String cpf) {
        for (Paciente p : pacientes) {
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        throw new RuntimeException("Paciente não encontrado!");
    }
    // UPDATE
    public void updatePaciente(String cpf, Paciente pacienteAtualizado) {
        for (int i = 0; i< pacientes.size(); i++) {
            Paciente p = pacientes.get(i);
            if (p.getCpf().equals(cpf)) {
                pacientes.set(i, pacienteAtualizado);
                return;
            }
        }
        throw new RuntimeException("Paciente não encontrado para atualização!");
    }

    // DELETE
    public void deletePaciente(String cpf) {
        Paciente paciente =  getPaciente(cpf);
        pacientes.remove(paciente);
    }
}

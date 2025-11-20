package com.vulkantech.salus.service;

import com.vulkantech.salus.model.Paciente;
import com.vulkantech.salus.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository; // injeção via Lombok

    // CREATE
    public Paciente addPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    // READ - listar todos
    public List<Paciente> getPacientes() {
        return pacienteRepository.findAll();
    }

    // READ - buscar por CPF
    public Paciente getPaciente(String cpf) {
        return pacienteRepository.findById(cpf).orElse(null);
    }

    // UPDATE
    public Paciente updatePaciente(String cpf, Paciente pacienteAtualizado) {
        Optional<Paciente> pacienteOpt = pacienteRepository.findById(cpf);
        if (pacienteOpt.isEmpty()) return null;

        Paciente paciente = pacienteOpt.get();
        paciente.setNome(pacienteAtualizado.getNome());
        paciente.setEmail(pacienteAtualizado.getEmail());
        paciente.setTelefone(pacienteAtualizado.getTelefone());
        paciente.setIdade(pacienteAtualizado.getIdade());
        paciente.setDoenca(pacienteAtualizado.getDoenca());

        return pacienteRepository.save(paciente);
    }

    // DELETE
    public boolean deletePaciente(String cpf) {
        if (!pacienteRepository.existsById(cpf)) return false;
        pacienteRepository.deleteById(cpf);
        return true;
    }
}

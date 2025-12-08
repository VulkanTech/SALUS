package com.vulkantech.salus.service;

import com.vulkantech.salus.model.Paciente;
import com.vulkantech.salus.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    // CREATE
    @Transactional
    public Paciente cadastrar(Paciente paciente) {
        if (pacienteRepository.existsById(paciente.getCpf())) {
            throw new RuntimeException("Já existe um Paciente com esse CPF!");
        }
        return pacienteRepository.save(paciente);
    }

    // READ - listar todos
    @Transactional(readOnly = true)
    public List<Paciente> listarTodos() {

        return pacienteRepository.findAll();
    }

    // READ - buscar por CPF
    @Transactional(readOnly = true)
    public Paciente buscarPorCpf(String cpf) {

        return pacienteRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));
    }

    // UPDATE
    @Transactional
    public Paciente atualizar(String cpf, Paciente dadosAtualizados) {
        Paciente paciente = buscarPorCpf(cpf);

        paciente.setNome(dadosAtualizados.getNome());
        paciente.setEmail(dadosAtualizados.getEmail());
        paciente.setTelefone(dadosAtualizados.getTelefone());
        paciente.setIdade(dadosAtualizados.getIdade());
        paciente.setDoenca(dadosAtualizados.getDoenca());

        return pacienteRepository.save(paciente);
    }

    // DELETE
    @Transactional
    public void deletar(String cpf) {
        Paciente paciente = buscarPorCpf(cpf);
        pacienteRepository.delete(paciente);
    }
}

package com.vulkantech.salus.service;

import com.vulkantech.salus.model.Medico;
import com.vulkantech.salus.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository; // injeção via Lombok

    // CREATE
    public Medico addMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    // READ - listar todos
    public List<Medico> getMedicos() {
        return medicoRepository.findAll();
    }

    // READ - buscar por CPF
    public Medico getMedico(String cpf) {
        return medicoRepository.findById(cpf).orElse(null);
    }

    // UPDATE
    public Medico updateMedico(String cpf, Medico medicoAtualizado) {
        Optional<Medico> medicoOpt = medicoRepository.findById(cpf);
        if (medicoOpt.isEmpty()) return null;

        Medico medico = medicoOpt.get();
        medico.setCrm(medicoAtualizado.getCrm());
        medico.setEspecialidade(medicoAtualizado.getEspecialidade());

        return medicoRepository.save(medico);
    }

    // DELETE
    public boolean deleteMedico(String cpf) {
        if (!medicoRepository.existsById(cpf)) return false;
        medicoRepository.deleteById(cpf);
        return true;
    }
}

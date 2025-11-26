package com.vulkantech.salus.service;

import com.vulkantech.salus.model.Medico;
import com.vulkantech.salus.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository; // injeção via Lombok

    // CREATE
    @Transactional
    public Medico addMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    // READ - listar todos
    @Transactional(readOnly = true)
    public List<Medico> getMedicos() {
        return medicoRepository.findAll();
    }

    // READ - buscar por CPF
    @Transactional(readOnly = true)
    public Medico getMedico(String cpf) {
        return medicoRepository.findById(cpf).orElse(null);
    }

    // UPDATE
    @Transactional
    public Medico updateMedico(String cpf, Medico medicoAtualizado) {
        Optional<Medico> medicoOpt = medicoRepository.findById(cpf);
        if (medicoOpt.isEmpty()) return null;

        Medico medico = medicoOpt.get();
        medico.setNome(medicoAtualizado.getNome());
        medico.setEmail(medicoAtualizado.getEmail());
        medico.setTelefone(medicoAtualizado.getTelefone());
        medico.setIdade(medicoAtualizado.getIdade());
        medico.setCrm(medicoAtualizado.getCrm());
        medico.setEspecialidade(medicoAtualizado.getEspecialidade());

        return medicoRepository.save(medico);
    }

    // DELETE
    @Transactional
    public boolean deleteMedico(String cpf) {
        if (!medicoRepository.existsById(cpf)) return false;
        medicoRepository.deleteById(cpf);
        return true;
    }
}

package com.vulkantech.salus.service;

import com.vulkantech.salus.model.Paciente;
import com.vulkantech.salus.model.Profissional;
import com.vulkantech.salus.repository.PacienteRepository;
import com.vulkantech.salus.repository.ProfissionalRepository;

import java.util.List;
import java.util.Optional;

public class ProfissionalService {
    private final ProfissionalRepository profissionalRepository;

    //injecao com construtor
    public ProfissionalService(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

    //CREATE
    public Profissional addProfissional(Profissional profissional) {
        return profissionalRepository.save(profissional);

    }

    //READ-individual
    public Profissional getProfissional(String cpf) {
        return profissionalRepository.findById(cpf).orElse(null);
    }

    //READ-geral
    public List<Profissional> getAllProfissional() {
        return profissionalRepository.findAll();

    }

    //UPDATE
    public Profissional updateProfissional(String cpf, Profissional profissional) {
        Optional<Profissional> profissionalOpt = profissionalRepository.findById(cpf);

        if (profissionalOpt.isPresent()) {
            //Pega o objeto profissional do Optional
            Profissional profissionalOld = profissionalOpt.get();

            profissionalOld.setNome(profissional.getNome());
            profissionalOld.setIdade(profissional.getIdade());
            profissionalOld.setTelefone(profissional.getTelefone());
            profissionalOld.setEmail(profissional.getEmail());
            return profissionalRepository.save(profissionalOld);

        }
        return null;
    }

    //DELETE
    public boolean deleteProfissional(String cpf) {
        if (profissionalRepository.findById(cpf).isPresent()) {
            profissionalRepository.deleteById(cpf);
            return true;
        }
        return false;
    }
}






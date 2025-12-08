package com.vulkantech.salus.service;

import com.vulkantech.salus.model.Medico;
import com.vulkantech.salus.repository.MedicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    // CREATE
    @Transactional
    public Medico cadastrar(Medico medico){
        if (medicoRepository.existsById(medico.getCpf())) {
            throw new RuntimeException("Já existe médico cadastrado com esse CPF!");
        }
        return medicoRepository.save(medico);
    }

    // READ - listar todos
    @Transactional(readOnly = true)
    public List<Medico> listarTodos() {

        return medicoRepository.findAll();
    }

    // READ - buscar por CPF
    @Transactional(readOnly = true)
    public Medico buscarPorCpf(String cpf){
        return medicoRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado!"));
    }

    // UPDATE
    @Transactional
    public Medico atualizar(String cpf, Medico dadosAtualizados) {
        Medico medico = buscarPorCpf(cpf);

        medico.setNome(dadosAtualizados.getNome());
        medico.setEmail(dadosAtualizados.getEmail());
        medico.setTelefone(dadosAtualizados.getTelefone());
        medico.setIdade(dadosAtualizados.getIdade());
        medico.setEspecialidade(dadosAtualizados.getEspecialidade());

        return medicoRepository.save(medico);
    }

    // DELETE
    @Transactional
    public void deletar(String cpf){
        Medico medico = buscarPorCpf(cpf);
        medicoRepository.delete(medico);
    }
}

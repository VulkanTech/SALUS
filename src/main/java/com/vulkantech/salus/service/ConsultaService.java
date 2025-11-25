package com.vulkantech.salus.service;
import com.vulkantech.salus.model.Consulta;
import com.vulkantech.salus.model.Medico;
import com.vulkantech.salus.model.Paciente;
import com.vulkantech.salus.repository.ConsultaRepository;
import org.springframework.stereotype.Service;
import com.vulkantech.salus.exception.ConsultaNaoEncontradaException;
import com.vulkantech.salus.exception.ConflitodeHorarioException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {
    private final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    // CREATE
    @Transactional
    public Consulta addConsulta(Medico medico, Paciente paciente, LocalDateTime dataHora) {
        if (consultaRepository.existsByMedico_CrmAndDataHora(medico.getCrm(), dataHora)) {
            throw new ConflitodeHorarioException("Esse médico já tem consulta neste horário.");
        } else if (consultaRepository.existsByPaciente_CpfAndDataHora(paciente.getCpf(), dataHora)) {
            throw new ConflitodeHorarioException("Esse paciente já tem consulta neste horário.");
        }

        Consulta novaConsulta = new Consulta(medico, paciente, dataHora);
        return consultaRepository.save(novaConsulta);
    }

    // READ - busca individual
    //busca por ID (se ele não achar mostra erro 404)
    @Transactional(readOnly = true)
    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new ConsultaNaoEncontradaException("Consulta não encontrada com o ID: " + id));
    }

    //READ - retorna todos os itens
    @Transactional(readOnly = true)
    public List<Consulta> getConsultas() {
        return consultaRepository.findAll();
    }


    //Retorna todas consultas do médico
    @Transactional(readOnly = true)
    public List<Consulta> listarConsultasDoMedico(Medico medico) {
        return consultaRepository.findAll()
                .stream()
                .filter(c -> c.getMedico().equals(medico))
                .toList();
    }

    // UPDATE


    // DELETE
    @Transactional //garante atomicidade - tudo ou nada
    public void cancelarConsulta(Long idConsulta) {
        Consulta consulta = buscarPorId(idConsulta);
        if (consulta == null) {
            throw new ConsultaNaoEncontradaException("Consulta não encontrada!");
        } else {
            consulta.cancelar();
            consultaRepository.save(consulta);
        }
    }
}
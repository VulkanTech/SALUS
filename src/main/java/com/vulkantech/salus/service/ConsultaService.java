package com.vulkantech.salus.service;
import com.vulkantech.salus.model.Consulta;
import com.vulkantech.salus.model.Medico;
import com.vulkantech.salus.model.Paciente;
import com.vulkantech.salus.repository.ConsultaRepository;
import org.springframework.stereotype.Service;
import com.vulkantech.salus.exception.ConsultaNaoEncontradaException;
import com.vulkantech.salus.exception.ConflitodeHorarioException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {
    private final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    // CREATE
    public Consulta addConsulta(Medico medico, Paciente paciente, LocalDateTime dataHora ) {
        if (consultaRepository.existsByMedico_CrmAndDataHora(medico.getCrm(), dataHora)) {
            throw new ConflitodeHorarioException("Esse médico já tem consulta neste horário.");
        }

        else if (consultaRepository.existsByPaciente_CpfAndDataHora(paciente.getCpf(), dataHora)) {
            throw new ConflitodeHorarioException("Esse paciente já tem consulta neste horário.");
        }

        Consulta novaConsulta = new Consulta(medico, paciente, dataHora);
        return consultaRepository.save(novaConsulta);
    }

    // READ
    //busca por ID (se ele não achar mostra erro 404)
    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new ConsultaNaoEncontradaException("Consulta não encontrada com o ID: " + id));
    }
    public List<Consulta> getConsultas() {
        return consultaRepository.findAll();
    }

    public List<Consulta> listarConsultasDoMedico(Medico medico) {
        return consultaRepository.findAll()
                .stream()
                .filter(c -> c.getMedico().equals(medico))
                .toList();
    }

    // UPDATE


    // DELETE
    public void cancelarConsulta(Long idConsulta) {
        Consulta consulta = buscarPorId(idConsulta);
        consulta.cancelar();
        consultaRepository.save(consulta);
    }
}
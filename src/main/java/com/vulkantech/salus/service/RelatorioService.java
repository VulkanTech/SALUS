package com.vulkantech.salus.service;

import com.vulkantech.salus.dto.RelatorioConsultaDTO;
import com.vulkantech.salus.model.Consulta;
import com.vulkantech.salus.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final ConsultaRepository consultaRepository;

    public List<RelatorioConsultaDTO> gerarRelatorioConsultas(Long medicoId, Long pacienteId) {

        List<Consulta> consultas;

        if (medicoId != null && pacienteId != null) {
            consultas = consultaRepository.findByMedicoIdAndPacienteId(medicoId, pacienteId);
        }
        else if (medicoId != null) {
            consultas = consultaRepository.findByMedicoId(medicoId);
        }
        else if (pacienteId != null) {
            consultas = consultaRepository.findByPacienteId(pacienteId);
        }
        else {
            consultas = consultaRepository.findAll();
        }

        return consultas.stream()
                .map(c -> new RelatorioConsultaDTO(
                        c.getId(),
                        c.getDataHora(),
                        c.getMedico().getNome(),
                        c.getPaciente().getNome()
                ))
                .toList();
    }
}

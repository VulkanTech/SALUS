package com.vulkantech.salus.service;

import com.vulkantech.salus.dto.RelatorioConsultaDTO;
import com.vulkantech.salus.model.Consulta;
import com.vulkantech.salus.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final ConsultaRepository consultaRepository;

    // Relatorio Geral
    public List<RelatorioConsultaDTO> gerarRelatorioConsultas(String medicoCpf, String pacienteCpf) {

        List<Consulta> consultas;

        if (medicoCpf != null && pacienteCpf != null) {
            consultas = consultaRepository.findByMedico_CpfAndPaciente_Cpf(medicoCpf, pacienteCpf);
        }
        else if (medicoCpf != null) {
            consultas = consultaRepository.findByMedico_Cpf(medicoCpf);
        }
        else if (pacienteCpf != null) {
            consultas = consultaRepository.findByPaciente_Cpf(pacienteCpf);
        }
        else {
            consultas = consultaRepository.findAll();
        }

        return consultas.stream()
                .map(this::toDTO)
                .toList();
    }


    // Relatorio por Medico
    public List<RelatorioConsultaDTO> listarPorMedico(String medicoCpf) {
        return consultaRepository.findByMedico_Cpf(medicoCpf)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // Relatorio por período
    public List<RelatorioConsultaDTO> listarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return consultaRepository.findByDataHoraBetween(inicio, fim)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // Converte pra dto
    private RelatorioConsultaDTO toDTO(Consulta c) {
        return new RelatorioConsultaDTO(
                c.getId(),
                c.getDataHora(),
                c.getMedico().getNome(),
                c.getPaciente().getNome()
        );
    }
}

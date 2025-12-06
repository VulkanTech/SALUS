package com.vulkantech.salus.repository;

import com.vulkantech.salus.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedico_CrmAndDataHora(String crm, LocalDateTime dataHora);
    boolean existsByPaciente_CpfAndDataHora(String cpf, LocalDateTime dataHora);

    List<Consulta> findByMedicoIdAndPacienteId(Long medicoId, Long pacienteId);

    List<Consulta> findByMedicoId(Long medicoId);

    List<Consulta> findByPacienteId(Long pacienteId);
}

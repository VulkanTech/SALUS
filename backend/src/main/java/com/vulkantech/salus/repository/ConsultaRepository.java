package com.vulkantech.salus.repository;

import com.vulkantech.salus.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByMedico_CpfAndDataHora(String medicoCpf, LocalDateTime dataHora);
    boolean existsByPaciente_CpfAndDataHora(String cpf, LocalDateTime dataHora);

    List<Consulta> findByMedico_CpfAndPaciente_Cpf(String medicoCpf, String pacienteCpf);

    List<Consulta> findByMedico_Cpf(String medicoCpf);

    List<Consulta> findByPaciente_Cpf(String pacienteCpf);

    List<Consulta> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
}

package com.vulkantech.salus.repository;

import com.vulkantech.salus.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedico_CrmAndDataHora(String crm, LocalDateTime dataHora);
}

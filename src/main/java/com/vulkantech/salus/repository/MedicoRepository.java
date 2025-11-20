package com.vulkantech.salus.repository;

import com.vulkantech.salus.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, String> {
}

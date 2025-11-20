package com.vulkantech.salus.repository;

import com.vulkantech.salus.model.Profissional;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface ProfissionalRepository extends JpaRepositoryImplementation<Profissional, String> {

     boolean existsByCpf(String cpf);


}

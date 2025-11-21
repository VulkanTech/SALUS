package com.vulkantech.salus.service;
import com.vulkantech.salus.model.Consulta;
import com.vulkantech.salus.model.Medico;
import com.vulkantech.salus.model.Paciente;
import org.springframework.stereotype.Service;
import com.vulkantech.salus.exception.ConsultaNaoEncontradaException;
import com.vulkantech.salus.exception.ConflitodeHorarioException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Service
public class ConsultaService {
    List<Consulta> consultas = new ArrayList<>();

    // CREATE
    public Consulta addConsulta(Medico medico, Paciente paciente, LocalDateTime dataHora ) {
        for (Consulta c : consultas) {
            if (c.getMedico().equals(medico) && c.getDataHora().equals(dataHora) && !c.isCancelar()) {
                throw new RuntimeException("Esse médico já tem consulta nesse horário.");
            }
            else if (c.getPaciente().equals(paciente) && c.getDataHora().equals(dataHora) && !c.isCancelar()) {
                throw new RuntimeException("Esse paciente já tem uma consulta marcada nesse horário.");
            }
            else if (c.getMedico().equals(medico) && c.getPaciente().equals(paciente) && c.getDataHora().equals(dataHora) && !c.isCancelar()) {
                throw new RuntimeException("Essa consulta já está marcada nesse horário.");
            }
        }

        Consulta novaConsulta = new Consulta(medico, paciente, dataHora);
        consultas.add(novaConsulta);
        return novaConsulta;
    }

    // READ
    public List<Consulta> getConsultas() {
        return consultas;
    }

    public List<Consulta> listarConsultasDoMedico(Medico medico) {
        List<Consulta> lista = new ArrayList<>();
        for (Consulta c : consultas) {
            if (c.getMedico().equals(medico)) {
                lista.add(c);
            }
        }
        return lista;
    }

    // UPDATE


    // DELETE
    public void cancelarConsulta(Consulta consulta) {
        consulta.cancelar();
    }
}

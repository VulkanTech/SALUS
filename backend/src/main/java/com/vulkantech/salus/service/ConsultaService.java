package com.vulkantech.salus.service;

import com.vulkantech.salus.exception.ConflitodeHorarioException;
import com.vulkantech.salus.exception.ConsultaNaoEncontradaException;
import com.vulkantech.salus.model.Consulta;
import com.vulkantech.salus.model.Medico;
import com.vulkantech.salus.model.Paciente;
import com.vulkantech.salus.repository.ConsultaRepository;
import com.vulkantech.salus.repository.MedicoRepository;
import com.vulkantech.salus.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public ConsultaService(
            ConsultaRepository consultaRepository,
            MedicoRepository medicoRepository,
            PacienteRepository pacienteRepository) {

        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    // CREATE
    @Transactional
    public Consulta agendar(String medicoCpf, String pacienteCpf, LocalDateTime dataHora) {
        Medico medico = medicoRepository.findById(medicoCpf)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        Paciente paciente = pacienteRepository.findById(pacienteCpf)
                .orElseThrow(() -> new RuntimeException("Paciente não Encontrado"));

        if (consultaRepository.existsByMedico_CpfAndDataHora(medicoCpf, dataHora)) {
            throw new ConflitodeHorarioException("Esse médico já possui consulta nesse horário");
        }

        if (consultaRepository.existsByPaciente_CpfAndDataHora(pacienteCpf, dataHora)) {
            throw new ConflitodeHorarioException("Esse paciente já possui consulta nesse horário");
        }

        Consulta consulta = Consulta.builder()
                .medico(medico)
                .paciente(paciente)
                .dataHora(dataHora)
                .build();

        return consultaRepository.save(consulta);

    }

    //READ - retorna todos os itens
    @Transactional(readOnly = true)
    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    // READ - busca individual
    //busca por ID (se ele não achar mostra erro 404)
    @Transactional(readOnly = true)
    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new ConsultaNaoEncontradaException("Consulta não encontrada com o ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Consulta> listarPorMedico(String medicoCpf) {
        return consultaRepository.findByMedico_Cpf(medicoCpf);
    }

    // UPDATE
    @Transactional
    public Consulta atualizar(Long idConsulta, String novoMedicoCpf, String novoPacienteCpf, LocalDateTime novaDataHora) {

        Consulta consulta = buscarPorId(idConsulta);

        Medico medico = medicoRepository.findById(novoMedicoCpf)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        Paciente paciente = pacienteRepository.findById(novoPacienteCpf)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        // Verificar conflito de horário para outro médico
        boolean medicoMudou = !consulta.getMedico().getCpf().equals(novoMedicoCpf);
        boolean pacienteMudou = !consulta.getPaciente().getCpf().equals(novoPacienteCpf);
        boolean horarioMudou = !consulta.getDataHora().equals(novaDataHora);

        if ((medicoMudou || horarioMudou) &&
                consultaRepository.existsByMedico_CpfAndDataHora(novoMedicoCpf, novaDataHora)) {

            throw new ConflitodeHorarioException("Este médico já possui outra consulta nesse horário.");
        }

        // Verificar conflito de horário para outro paciente

        if ((pacienteMudou || horarioMudou) &&
                consultaRepository.existsByPaciente_CpfAndDataHora(novoPacienteCpf, novaDataHora)) {

            throw new ConflitodeHorarioException("Este paciente já possui outra consulta nesse horário.");
        }

        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setDataHora(novaDataHora);

        return consultaRepository.save(consulta);
    }

    // DELETE
    @Transactional //garante atomicidade - tudo ou nada
    public void cancelarConsulta(Long idConsulta) {
        Consulta consulta = buscarPorId(idConsulta);
        if (consulta == null) {
            throw new ConsultaNaoEncontradaException("Consulta não encontrada!");
        } else {
            consulta.cancelar();
        }
    }
}
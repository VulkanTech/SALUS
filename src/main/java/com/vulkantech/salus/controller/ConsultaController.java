package com.vulkantech.salus.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vulkantech.salus.dto.AgendamentoRequestDTO;
import com.vulkantech.salus.model.Medico;
import com.vulkantech.salus.model.Paciente;
import com.vulkantech.salus.model.Consulta;
import com.vulkantech.salus.service.ConsultaService;
import com.vulkantech.salus.repository.MedicoRepository;
import com.vulkantech.salus.repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
@Tag(name = "Consultas", description = "Gerenciamento de consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    //POST - Agendamento,@valid e conflito de horario
    @PostMapping("/{id}")
    @Operation(summary = "Agenda nova consulta")
    public ResponseEntity<Consulta> agendar(@RequestBody @Valid AgendamentoRequestDTO dados) {
        Medico medico = medicoRepository.findById(String.valueOf(dados.getMedicoId()))
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        Paciente paciente = pacienteRepository.findById(String.valueOf(dados.getPacienteId()))
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Consulta novaConsulta = consultaService.addConsulta(
                medico,
                paciente,
                dados.getDataHora()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(novaConsulta);

    }
    //Valid- faz a validação dos campos
    //RequestBody - data binding- recebe o json da requisição e transforma no objeto java

    // GET - Retorna todos os itens
    @GetMapping
    @Operation(summary = "Lista todas as consultas")
    public ResponseEntity<List<Consulta>> listar() {
        List<Consulta> consultas = consultaService.getConsultas();
        return ResponseEntity.ok(consultas);
    }

    // GET - Busca individual por ID
    @GetMapping("/{id}")
    @Operation(summary = "Busca consulta por ID")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Long id) {
        Consulta consulta = consultaService.buscarPorId(id);
        return ResponseEntity.ok(consulta);
    }

    // GET - Busca consultas pelo CRM do médico
    @GetMapping("/medico/{crm}")
    @Operation(summary = "Busca consultas pelo CRM do médico")
    public ResponseEntity<List<Consulta>> listarPorMedico(@PathVariable String crm) {
        List<Consulta> consultas = consultaService.listarConsultasDoMedico(crm);
        return ResponseEntity.ok(consultas);
    }

    //PUT
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza consulta")
    public ResponseEntity<Consulta> editar(@PathVariable Long id, @RequestBody @Valid AgendamentoRequestDTO dados) {

        Medico novoMedico = medicoRepository.findById(String.valueOf(dados.getMedicoId()))
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        Paciente novoPaciente = pacienteRepository.findById(String.valueOf(dados.getPacienteId()))
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Consulta consultaAtualizada = consultaService.atualizarConsulta(
                id,
                novoMedico,
                novoPaciente,
                dados.getDataHora()
        );

        return ResponseEntity.ok(consultaAtualizada);
    }

    //DELETE
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta consulta")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        consultaService.cancelarConsulta(id);
        return ResponseEntity.noContent().build();
    }
}

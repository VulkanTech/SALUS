package com.vulkantech.salus.controller;

import com.vulkantech.salus.dto.MedicoRequestDTO;
import com.vulkantech.salus.dto.MedicoResponseDTO;
import com.vulkantech.salus.model.Medico;
import com.vulkantech.salus.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
@Tag(name = "Médicos", description = "Gerenciamento de médicos")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    // CREATE
    @PostMapping
    @Operation(summary = "Cadastra um novo médico")
    public ResponseEntity<MedicoResponseDTO> cadastrar(@Valid @RequestBody MedicoRequestDTO dto) {

        Medico medico = new Medico();
        medico.setCpf(dto.getCpf());
        medico.setNome(dto.getNome());
        medico.setEmail(dto.getEmail());
        medico.setTelefone(dto.getTelefone());
        medico.setIdade(dto.getIdade());
        medico.setEspecialidade(dto.getEspecialidade());

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(medicoService.cadastrar(medico)));
    }

    // READ - listar todos
    @GetMapping
    @Operation(summary = "Listar todos os médicos")
    public List<MedicoResponseDTO> listar(){
        return medicoService.listarTodos()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // READ - buscar por CPF
    @GetMapping("/{cpf}")
    @Operation(summary = "Buscar médico por CPF")
    public MedicoResponseDTO buscar(@PathVariable String cpf){

        return toResponse(medicoService.buscarPorCpf(cpf));
    }

    // UPDATE
    @PutMapping("/{cpf}")
    @Operation(summary = "Atualizar dados do médico por CPF")
    public MedicoResponseDTO atualizar(@PathVariable String cpf, @Valid @RequestBody MedicoRequestDTO dto) {
        Medico medico = new Medico();
        medico.setNome(dto.getNome());
        medico.setEmail(dto.getEmail());
        medico.setTelefone(dto.getTelefone());
        medico.setIdade(dto.getIdade());
        medico.setEspecialidade(dto.getEspecialidade());

        return toResponse(medicoService.atualizar(cpf, medico));
    }


    // DELETE
    @DeleteMapping("/{cpf}")
    @Operation(summary = "Excluir médico por CPF")
    public ResponseEntity<Void> deletar(@PathVariable String cpf){
    medicoService.deletar(cpf);
    return ResponseEntity.noContent().build();
    }

    // Entity → Response
    private MedicoResponseDTO toResponse(Medico medico) {
        MedicoResponseDTO dto = new MedicoResponseDTO();
        dto.setCpf(medico.getCpf());
        dto.setNome(medico.getNome());
        dto.setEmail(medico.getEmail());
        dto.setTelefone(medico.getTelefone());
        dto.setIdade(medico.getIdade());
        dto.setEspecialidade(medico.getEspecialidade());
        return dto;
    }
}

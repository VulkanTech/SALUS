package com.vulkantech.salus.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Locked;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vulkantech.salus.dto.AgendamentoRequestDTO;
import com.vulkantech.salus.model.Consulta;
import com.vulkantech.salus.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@Tag(name = "Consultas", description = "Gerenciamento de consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    //CREATE - Agendamento,@valid e conflito de horario
    @PostMapping("/{id}")
    public ResponseEntity<Consulta> agendar(@RequestBody @Valid AgendamentoRequestDTO dados) {
        return null;

    }
    //Valid- faz a validação dos campos
    //RequestBody - data binding- recebe o json da requisição e transforma no objeto java

    //READ
    @GetMapping
    public ResponseEntity<Consulta> consultar(@RequestParam @Valid Long idConsulta) {
        return null;
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Consulta> editar(@RequestBody @Valid AgendamentoRequestDTO dados) {
        return null;
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Consulta> deletar(@RequestBody @Valid AgendamentoRequestDTO dados) {
        return null;
    }

}

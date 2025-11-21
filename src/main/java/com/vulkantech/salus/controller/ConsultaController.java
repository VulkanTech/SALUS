package com.vulkantech.salus.controller;


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
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    //POST - Agendamento,@valid e conflito de horario
    @PostMapping
    public ResponseEntity<Consulta> agendar(@RequestBody @Valid AgendamentoRequestDTO dados) {
        return null;

    }


}

package com.vulkantech.salus.controller;

import com.vulkantech.salus.model.Medico;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {
    @PostMapping()
    public Medico create(@RequestBody Medico medico) {

    }

    @GetMapping()
    @GetMapping()
    @PutMapping()
    @DeleteMapping()
}


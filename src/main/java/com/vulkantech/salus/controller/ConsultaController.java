package com.vulkantech.salus.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    //teste pra ver se o servidor inicia
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }
}

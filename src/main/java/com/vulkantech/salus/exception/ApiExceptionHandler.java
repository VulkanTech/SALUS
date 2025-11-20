
package com.vulkantech.salus.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.vulkantech.salus.exception.ConflitodeHorarioException;
import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    //metodo generico para criar erro padronizado
    private ErrorResponse criarErro(HttpStatus status, String mensagem, HttpServletRequest request) {
        return new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                mensagem,
                request.getRequestURI()
        );
    }
//handlers especificos
//ResponseEntity-> objeto spring  pra construir respostas http completas-status,body,headers

    //409-conflict
    @ExceptionHandler(ConflitodeHorarioException.class)
    public ResponseEntity<ConflitodeHorarioException> handleConflitodeHorarioException(ConflitodeHorarioException ex, HttpServletRequest request) {

    }



    //404-not found
    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleConsultaNaoEncontrada(Exception ex, HttpServletRequest request) {


    }

    //400-bad request
    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleDadosInvalidos(Exception ex, HttpServletRequest request) {

    }




}

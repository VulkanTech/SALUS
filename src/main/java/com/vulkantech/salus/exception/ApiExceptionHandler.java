
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
    public ResponseEntity<ErrorResponse> handleConflitodeHorarioException(ConflitodeHorarioException ex, HttpServletRequest request) {
        ErrorResponse erro = criarErro(HttpStatus.CONFLICT, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);

    }

    //404-not found
    @ExceptionHandler(ConsultaNaoEncontradaException.class)
    public ResponseEntity<ErrorResponse> handleConsultaNaoEncontrada(Exception ex, HttpServletRequest request) {
        ErrorResponse erro = criarErro(HttpStatus.NOT_FOUND, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    //400-bad request
    @ExceptionHandler(DadosInvalidosException.class)
    public ResponseEntity<ErrorResponse> handleDadosInvalidos(Exception ex, HttpServletRequest request) {
        ErrorResponse erro = criarErro(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    //404-not found ->generico
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleRecursoNaoEncontrado(Exception ex, HttpServletRequest request) {
        ErrorResponse erro = criarErro(HttpStatus.NOT_FOUND, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }



}

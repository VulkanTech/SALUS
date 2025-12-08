
package com.vulkantech.salus.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintDeclarationException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.vulkantech.salus.exception.ConflitodeHorarioException;
import com.vulkantech.salus.exception.ErrorResponse;

import java.time.LocalDateTime;
import java.util.stream.Collectors;


@RestControllerAdvice
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
    public ResponseEntity<ErrorResponse> handleConsultaNaoEncontrada(ConsultaNaoEncontradaException ex, HttpServletRequest request) {
        ErrorResponse erro = criarErro(HttpStatus.NOT_FOUND, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    //400-bad request
    @ExceptionHandler(DadosInvalidosException.class)
    public ResponseEntity<ErrorResponse> handleDadosInvalidos(DadosInvalidosException ex, HttpServletRequest request) {
        ErrorResponse erro = criarErro(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    //404-not found ->generico
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErrorResponse> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex, HttpServletRequest request) {
        ErrorResponse erro = criarErro(HttpStatus.NOT_FOUND, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    //400-bad request - erros do @valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ErrorResponse erro = criarErro(HttpStatus.BAD_REQUEST, mensagem, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    //400-bad request - erro de validação
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {
        String mensagem = ex.getConstraintViolations()
                .stream()
                .map(v -> v.getPropertyPath() + (": ") + v.getMessage())
                .collect(Collectors.joining("; "));

        ErrorResponse erro = criarErro(HttpStatus.BAD_REQUEST, mensagem, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}









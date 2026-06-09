package com.acadimia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> errosDeValidacao(MethodArgumentNotValidException erro){
        Map<String, String> erros = new HashMap<>();

        erro.getBindingResult().getAllErrors().forEach(( error) -> {
            String campoQueDeuErro = ((FieldError) error).getField();
            String mensagemErro = error.getDefaultMessage();

            erros.put(campoQueDeuErro, mensagemErro);
        });

        return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CpfJaCadastradoException.class)
    public ResponseEntity<Map<String, String>> handleConflito(CpfJaCadastradoException erro){
        Map<String, String> error = new HashMap<>();

        error.put("erro", erro.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlunoNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(AlunoNotFoundException erro){
        Map<String, String> error = new HashMap<>();

        error.put("erro", erro.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}

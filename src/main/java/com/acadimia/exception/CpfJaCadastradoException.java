package com.acadimia.exception;

public class CpfJaCadastradoException extends RuntimeException {
    public CpfJaCadastradoException(String cpf) {
        super("CPF, já cadastrado " + cpf);
    }
}

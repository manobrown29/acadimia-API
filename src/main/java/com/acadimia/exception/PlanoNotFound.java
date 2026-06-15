package com.acadimia.exception;

import com.acadimia.model.Plano;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PlanoNotFound extends RuntimeException {
    public PlanoNotFound(String planoInformado) {
        super("Plano inválido " + planoInformado + ". Os planos disponíveis são: " +
                Arrays.stream(Plano.values())
                        .map(plano -> plano.name())
                        .collect(Collectors.joining(", ")));
    }
}

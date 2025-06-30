package com.arsw.conexionConValidacion.adapter;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PromptValidatorAdapter {

    private final List<String> mensajesInvalidos = Arrays.asList(
            "hola", "buenos dias", "buenas tardes", "buenas noches",
            "hi", "hello", "hey", "qué tal", "que tal", "saludos"
    );

    public boolean esValido(String prompt) {
        if (prompt == null || prompt.trim().isEmpty()) return false;

        String promptLimpio = prompt.trim().toLowerCase();
        return mensajesInvalidos.stream()
                .noneMatch(invalido -> promptLimpio.equals(invalido) || promptLimpio.contains(invalido));
    }
}
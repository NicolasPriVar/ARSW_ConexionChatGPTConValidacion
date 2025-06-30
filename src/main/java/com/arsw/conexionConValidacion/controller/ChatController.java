package com.arsw.conexionConValidacion.controller;



import com.arsw.conexionConValidacion.adapter.IAiAdapter;
import com.arsw.conexionConValidacion.adapter.PromptValidatorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private IAiAdapter chatGptAdapter;

    @Autowired
    private PromptValidatorAdapter validator;

    @PostMapping
    public String procesarPregunta(@RequestBody Map<String, String> body) {
        String prompt = body.get("prompt");

        if (!validator.esValido(prompt)) {
            return "Por favor, escribe una pregunta más específica o relevante.";
        }

        return chatGptAdapter.generateResponse(prompt);
    }
}
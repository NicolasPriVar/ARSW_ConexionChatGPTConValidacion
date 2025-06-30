package com.arsw.conexionConValidacion.adapter;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ChatGptAdapter implements IAiAdapter {

    @Value("${api.chatgpt.apiToken}")
    private String apikey;

    @Value("${api.chatgpt.url}")
    private String endpoint;

    private final HttpClient client = HttpClient.newHttpClient();

    @Override
    public String generateResponse(String input) {
        String body = "{ \"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"" + input.replace("\"", "\\\"") + "\"}] }";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apikey)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

            if (json.has("error")) {
                return "Error de OpenAI: " + json.getAsJsonObject("error").get("message").getAsString();
            }

            return json.getAsJsonArray("choices")
                    .get(0).getAsJsonObject()
                    .getAsJsonObject("message")
                    .get("content").getAsString();

        } catch (Exception e) {
            return "Error al conectar con OpenAI: " + e.getMessage();
        }
    }
}
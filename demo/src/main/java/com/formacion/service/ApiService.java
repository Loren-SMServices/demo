package com.formacion.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formacion.entities.UserApiResponse;
import com.formacion.entities.UserApiResponse.User;

import lombok.extern.java.Log;

@Service
@Log
public class ApiService {

    private final WebClient webClient;

    public ApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<User> getApiDataList() {
        String url = "https://dummyjson.com/users"; // URL de la API
        String response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class) // Recibe como texto
                .block();
        log.info(response);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Convierte el JSON al objeto `UsersResponse`
            UserApiResponse usersResponse = objectMapper.readValue(response, UserApiResponse.class);
            
            return usersResponse.getUsers(); // Devuelve la lista de usuarios
        } catch (Exception e) {
            throw new RuntimeException("Error al mapear el JSON a UsersResponse.", e);
        }
    }
}


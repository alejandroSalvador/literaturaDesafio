package com.alura.literaturaDesafio.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GutendexClient {

    private static final String BASE_URL = "https://gutendex.com/books/";
    private final RestTemplate restTemplate = new RestTemplate();





    public GutendexResponseDTO buscaPorTitulo(String titulo) {

        String url = UriComponentsBuilder
                .fromUriString(BASE_URL)
                .queryParam("search", titulo)
                .encode()   // 👈 IMPORTANTE
                .toUriString();

        String json = restTemplate.getForObject(url, String.class);
        System.out.println("URL => " + url);
        System.out.println("JSON => " + json);

        // 🔁 Luego lo mapeamos normalmente al DTO
        return restTemplate.getForObject(url, GutendexResponseDTO.class);
    }
    }


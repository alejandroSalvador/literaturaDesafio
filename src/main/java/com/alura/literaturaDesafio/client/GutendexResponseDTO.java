package com.alura.literaturaDesafio.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GutendexResponseDTO(
        Integer count,
        @JsonProperty("results") List<LibroDTO> results
) {
}

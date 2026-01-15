package com.alura.literaturaDesafio.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AutorDTO(

        @JsonProperty("name") String nombre,
        @JsonProperty("birth_year") Integer anioNacimiento,
        @JsonProperty("death_year") Integer anioFallecimiento

) {
}

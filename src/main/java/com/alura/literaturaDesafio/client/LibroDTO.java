package com.alura.literaturaDesafio.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDTO(

        @JsonProperty("title") String titulo,
        @JsonProperty("lenguages") List<String> idiomas,
        @JsonProperty("download_count") Integer descargas,
        @JsonProperty("authors") List<AutorDTO> autores

        ) {



}

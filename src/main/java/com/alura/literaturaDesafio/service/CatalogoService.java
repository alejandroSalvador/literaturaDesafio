package com.alura.literaturaDesafio.service;

import com.alura.literaturaDesafio.client.AutorDTO;
import com.alura.literaturaDesafio.client.GutendexClient;
import com.alura.literaturaDesafio.client.GutendexResponseDTO;
import com.alura.literaturaDesafio.client.LibroDTO;
import com.alura.literaturaDesafio.domain.Autor;
import com.alura.literaturaDesafio.domain.Libro;
import com.alura.literaturaDesafio.repository.AutorRepository;
import com.alura.literaturaDesafio.repository.LibroRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CatalogoService {

    private final GutendexClient gutendexClient;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public CatalogoService(GutendexClient gutendexClient, LibroRepository libroRepository, AutorRepository autorRepository) {
        this.gutendexClient = gutendexClient;
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    //Opcion 1 del menú: buscar por titulo
    @Transactional
    public Optional<Libro> buscarYRegistrarPorTitulo(String tituloBuscado){
        GutendexResponseDTO respuesta = gutendexClient.buscaPorTitulo(tituloBuscado);


        if (respuesta == null || respuesta.results() == null || respuesta.results().isEmpty()){
            return Optional.empty();
        }

        LibroDTO libroDTO = respuesta.results().get(0);

        Optional<Libro> existente = libroRepository.findByTituloIgnoreCase(libroDTO.titulo());
        if (existente.isPresent()){
            return existente;
        }

        //Proteccion para evitar nullPointerException

        String idiomas = (libroDTO.idiomas() ==null || libroDTO.idiomas().isEmpty())
                ? ""
                : String.join(",", libroDTO.idiomas());

        Libro libro = new Libro(
                libroDTO.titulo(),
                idiomas,
                libroDTO.descargas()
        );

        //Autores
        if (libroDTO.autores() != null){
            libroDTO.autores().forEach(autorDTO -> {
                Autor autor = autorRepository.findByNombreIgnoreCase(autorDTO.nombre())
                        .orElseGet(() -> autorRepository.save(
                                new Autor(autorDTO.nombre(), autorDTO.anioNacimiento(), autorDTO.anioFallecimiento())
                        ));
                libro.agregarAutor(autor);
            });
        }

        Libro guardado = libroRepository.save(libro);
        return Optional.of(guardado);
    }

    //opcion 2 del menu
    @Transactional(readOnly = true)
    public List<Libro> listarLibros() {
        return libroRepository.findAllConAutores();
    }

    //opción 3
    public List<Autor> listarAutores(){
        return autorRepository.findAll();
    }

    //opcion 4
    public List<Autor> listarAutoresVivos(int anio){
        return autorRepository.autoresVivosEn(anio);
    }

    //opcion 5
    public List<Libro> listarLibrosPorIdiomas(String idioma){
        return libroRepository.findByIdiomasContainingIgnoreCase(idioma);
    }
}

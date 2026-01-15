package com.alura.literaturaDesafio.repository;

import com.alura.literaturaDesafio.domain.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTituloIgnoreCase(String titulo);

    List<Libro> findByIdiomasContainingIgnoreCase(String idioma);


    @Query("SELECT DISTINCT l FROM Libro l LEFT JOIN FETCH l.autores")
    List<Libro> findAllConAutores();
}

package com.alura.literaturaDesafio.repository;

import com.alura.literaturaDesafio.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombreIgnoreCase(String nombre);

    @Query("""
    SELECT a FROM Autor a
    WHERE a.anioNacimiento IS NOT NULL
      AND a.anioNacimiento <= :anio
      AND (a.anioFallecimiento IS NULL OR a.anioFallecimiento >= :anio)
    ORDER BY a.nombre
""")
    List<Autor> autoresVivosEn(@Param("anio") int anio);
}
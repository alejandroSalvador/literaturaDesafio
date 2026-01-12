package com.alura.literaturaDesafio.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "libros")

public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String titulo;

    @Column(length = 50)
    private String idiomas;

    private Integer descargas;

    @ManyToMany
    @JoinTable(
            name = "libros_autores",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor>autores = new HashSet<>();

    public Libro() {
    }

    public Libro(String titulo, String idiomas, Integer descargas) {
        this.titulo = titulo;
        this.idiomas = idiomas;
        this.descargas = descargas;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public Set<Autor> getAutores() {
        return autores;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public void agregarAutor(Autor autor){
        this.autores.add(autor);
        autor.getLibros().add(this);
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", idiomas='" + idiomas + '\'' +
                ", descargas=" + descargas +
                ", autores=" + autores +
                '}';
    }
}

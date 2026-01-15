package com.alura.literaturaDesafio.principal;

import com.alura.literaturaDesafio.service.CatalogoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal implements CommandLineRunner {

    private final CatalogoService catalogoService;

    public Principal(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    @Override
    public void run(String... args) throws Exception {
        mostrarMenu();
    }

    private void mostrarMenu() {

        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0){
            System.out.println("""
                     📚 MENÚ LITERATURA
                        1 - Buscar libro por título
                        2 - Listar libros registrados
                        3 - Listar autores registrados
                        4 - Listar autores vivos en un año
                        5 - Listar libros por idioma
                        0 - Salir

                    """);
            System.out.println("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion){
                case 1:
                    buscarLibroPorTitulo(scanner);
                    break;
                case 2:
                    catalogoService.listarLibros()
                            .forEach(System.out::println);
                    break;
                case 3:
                    catalogoService.listarAutores()
                            .forEach(System.out::println);
                    break;
                case 4: listarAutoresVivos(scanner);
                    break;
                case 5:
                    listarLibrosPorIdioma(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo");;
                    break;
                default:
                    System.out.println("Opción Inválida");
            }


        }
    }



    private void buscarLibroPorTitulo(Scanner scanner) {
        System.out.println("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();

        catalogoService.buscarYRegistrarPorTitulo(titulo)
                .ifPresentOrElse(
                        libro -> System.out.println("Resultado: \n" + libro),()-> System.out.println("No se encontraron resultados")
                );
    }

    private void listarAutoresVivos(Scanner scanner) {
        System.out.println("Ingrese el año: ");
        int anio = scanner.nextInt();
        scanner.nextLine();

        catalogoService.listarAutoresVivos(anio)
                .forEach(System.out::println);
    }

    private void listarLibrosPorIdioma(Scanner scanner) {
        System.out.println("Ingrese idioma (ej: es, en, fr): ") ;
        String idioma = scanner.nextLine();

        catalogoService.listarLibrosPorIdiomas(idioma)
                .forEach(System.out::println);
    }



}

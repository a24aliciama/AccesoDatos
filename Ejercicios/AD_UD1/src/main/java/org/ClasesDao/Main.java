package org.ClasesDao;

import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClasificacionFileDAO clasificacionDAO = new ClasificacionFileDAO(Paths.get("./src/main/resources/clasificacionesDAO"));

        // Clasificación por defecto (Liga ACB)
        Clasificacion clasificacion = new Clasificacion("Liga ACB");

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("a. Añadir equipo");
            System.out.println("b. Mostrar clasificación");
            System.out.println("c. Guardar clasificación");
            System.out.println("d. Cargar clasificación");
            System.out.println("e. Salir");
            System.out.print("Selecciona una opción: ");

            String opcion = scanner.nextLine().toLowerCase();

            switch (opcion) {
                case "a":  // Añadir equipo
                    System.out.println("Introduce el nombre del equipo:");
                    String nombre = scanner.nextLine();
                    System.out.println("Introduce el número de victorias:");
                    int victorias = Integer.parseInt(scanner.nextLine());
                    System.out.println("Introduce el número de derrotas:");
                    int derrotas = Integer.parseInt(scanner.nextLine());
                    System.out.println("Introduce los puntos a favor:");
                    int puntosAFavor = Integer.parseInt(scanner.nextLine());
                    System.out.println("Introduce los puntos en contra:");
                    int puntosEnContra = Integer.parseInt(scanner.nextLine());

                    // Crear nuevo equipo y añadirlo a la clasificación
                    Equipo equipo = new Equipo(nombre, victorias, derrotas, puntosAFavor, puntosEnContra);
                    if (clasificacion.addEquipo(equipo)) {
                        System.out.println("Equipo añadido con éxito.");
                    } else {
                        System.out.println("El equipo ya está en la clasificación.");
                    }
                    break;

                case "b":  // Mostrar clasificación
                    System.out.println("Clasificación actual:");
                    System.out.println(clasificacion);
                    break;

                case "c":  // Guardar clasificación
                    if (clasificacionDAO.save(clasificacion)) {
                        System.out.println("Clasificación guardada con éxito.");
                    } else {
                        System.out.println("Error al guardar la clasificación.");
                    }
                    break;

                case "d":  // Cargar clasificación
                    //System.out.println("Introduce el nombre de la competición a cargar:");
                    String competicion = "Liga ACB";
                    Clasificacion cargada = clasificacionDAO.get(competicion);
                    if (cargada != null) {
                        clasificacion = cargada;
                        System.out.println("Clasificación cargada con éxito.");
                    } else {
                        System.out.println("Error al cargar la clasificación.");
                    }
                    break;

                case "e":  // Salir
                    System.out.println("¿Deseas guardar antes de salir? (s/n)");
                    String respuesta = scanner.nextLine().toLowerCase();
                    if (respuesta.equals("s")) {
                        if (clasificacionDAO.save(clasificacion)) {
                            System.out.println("Clasificación guardada antes de salir.");
                        } else {
                            System.out.println("Error al guardar la clasificación.");
                        }
                    }
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }
}
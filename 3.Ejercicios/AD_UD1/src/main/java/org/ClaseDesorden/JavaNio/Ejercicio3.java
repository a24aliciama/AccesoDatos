package org.ClaseDesorden.JavaNio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Ejercicio3 {

    /**
     * Ejercicio 3. Contenido de un directorio con list.
     * a) Escribid un programa en Java que, empleando las clases de Java NIO 2, liste los
     * archivos de un directorio por medio del método list(). Debe mostrar sólo los
     * archivos fuente Java, no los directorios que contiene. Recuerda el uso de filtros en
     * Stream y de forEach.
     * b) Haz el mismo ejercicio con el método list() de File y compara el tiempo de
     * ejecución en cada caso
    */

    public static void main(String[] args){

        Path directorio = Paths.get("C:/Users/a24aliciama/Desktop/Alky");

        try (Stream<Path> archivos = Files.list(directorio)){
            archivos.forEach(archivo ->{
                System.out.println(archivo.getFileName());
            });
        }catch (IOException e){
            System.err.println("Error " + e.getMessage());
        }

        //le falta listar solo los javas 
    }

}

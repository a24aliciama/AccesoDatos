package org.ClaseDesorden.JavaNio;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio2
{
    /** Escribir un programa en Java que, empleando las clases de Java NIO 2 Path y
    File, cree un directorio (con toda la Ruta) y un archivo vacío dentro de ese directorio.
     */
    public static void main(String[] args) throws IOException {

        Path directorio = Paths.get("C:/Users/a24aliciama/Desktop/Carpeta");
        Path archivo = directorio.resolve("archivo.txt");

        try{
            Files.createDirectories(directorio);
            Files.createFile(archivo);

            System.out.println("directorio creado");
            System.out.println("Archivo creado");

        }catch (FileAlreadyExistsException e){
            System.out.println("Error, ya existe el archivo");
        }


    }
}

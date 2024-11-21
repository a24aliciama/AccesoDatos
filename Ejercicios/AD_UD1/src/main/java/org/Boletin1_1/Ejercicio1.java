package org.Boletin1_1;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio1 {
    /**
     * EJERCICIO 1. COPIA DE ARCHIVOS CON BUFFER
     * Se realice un programa para copiar archivos. El programa debe recoger el
     * nombre del archivo origen y destino. Se existe debe solicitar confirmación sobrescribir.
     * Úsese I/O con buffer y métodos estáticos (tenga en cuenta que los archivos pueden ser
     * binarios).
     * a) Para la lectura desde teclado puede emplearse la clase Scanner.
     * b) Realiza el mismo ejercicio, pero empleando entradas desde ventana con
     * JFileChooser y mensajes de error en JOptionPane, si los hay.
     * c) Realiza un programa que lea con un JOptionPane pida una URL y para
     * posteriormente abrir un JFileChooser para guardarlo en el disco local.
     * Ayuda: para abrir un flujo de entrada a una URL puede hacerse con el
     * método openStream() de URL. Ten en cuenta que puede lanzar excepciones.
     * InputStream in = new URL(FILE_URL).openStream();
     * d) Mejora el aparado a) para que la lectura de los datos lo haga en bloques
     * (buffer) y no byte a byte.
     */
    public static void main(String[] args) {


        //arhivo de origen
        File entrada = new File("src/main/resources/boletin1/entrada1");

        if (entrada.exists()){
            System.out.println("archivo encontrado");
        }else{
            entrada.mkdirs();
            System.out.println("archivo creado");
        }

        String destino = "src/main/Salidas/boletin1" + entrada.getName();

        File salida = new File(destino);



    }
}

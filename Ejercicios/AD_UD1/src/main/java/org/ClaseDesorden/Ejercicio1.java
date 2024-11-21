package org.ClaseDesorden;

import java.io.File;
import java.io.IOException;
import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Ejercicio1 {
    public static void main(String[] args) throws IOException {


        /*
        Ejercicio 1. Creación y lectura de archivos con File
        Debes trabajar únicamente con métodos de la clase File.

        Realiza los siguientes pasos:

        Crea un archivo de texto llamado prueba.txt en el directorio actual de tu proyecto, sólo si no existe.
        Escribe un programa que cree un objeto File para el archivo prueba.txt y compruebe si el archivo existe.
        Si el archivo existe, muestra la ruta absoluta, nombre del archivo, tamaño, última modificación y si es un directorio.
        Si el archivo no existe, muestra un mensaje que lo indique y crea uno temporal.
        */

        File f = new File("Prueba.txt");

        //JFileChooser fc = new JFileChooser();
       // if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
           // f = fc.getSelectedFile();

        if(f.exists()){
            System.out.println( "Ruta: " + f.getAbsolutePath() + "\n"
                    + "Nome: "  + f.getName() + "\n"
                    + "Espacio Ocupado: "  + f.getTotalSpace() + "\n"
                    + "Tamanho: " + f.length() +" bytes \n"
                    + "Ultima Modificacion: "  + new Date(f.lastModified()) + "\n"
                    + "Es un directorio?: " );
            System.out.println(f.isDirectory()? "Si, es un directorio" : "No, es un Archivo");
        }else{
            System.out.println("no existe. Creamos uno");
            try {
                if(f.createNewFile()){
                    System.out.println(".");

                }else{
                    System.out.println("error");
                }
                //File.createTempFile( "Prueba", ".txt");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
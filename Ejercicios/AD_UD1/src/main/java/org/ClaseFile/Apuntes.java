package org.ClaseFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Apuntes {
    public static void main(String[] args) throws IOException {
        /**
         * Escribes en la consola te devuelve lalinea que has escrito. Para finalizar programa _EXIT_
         */
       Reader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        String linha;
        while(!(linha = br.readLine()).equalsIgnoreCase("_EXIT_")){
            System.out.println("linha = " + linha);
        }
    }
}

package org.ClasesCasa;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static java.lang.System.*;

public class GsonCode {
        public static void main(String[] args) {
            Gson gson = new Gson();

            // Deserializacion de una cadena
            String nome = gson.fromJson("\"Sylvia Plath\"", String.class);
            out.println(nome);

            // Serializacion de un entero
            gson.toJson(256, out); // por pantalla
            out.println(); // salto de línea.

            // Serialización
            gson.toJson("<html>", out); // por pantalla.
            out.println(); // salto de línea

            // Gson personalizado deshabilitando el escapado de HTML
            gson = new GsonBuilder().disableHtmlEscaping().create();
            gson.toJson("<html>", out); // Sin escapar HTML
            out.println();

            String nome1 = gson.fromJson("\"\\u003chtml\\u003e\"", String.class);
            out.println(nome1);
        }
    }


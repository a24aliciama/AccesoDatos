package GSON.EjExamen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class main {
    public static void main(String[] args) {

        Date fecha = Date.from(LocalDateTime.of
                (2024, 2, 6, 10, 2)
                .atZone(ZoneId.systemDefault()).toInstant());
        Examen ex = new Examen("datos",fecha);
        ex.addParticipantes("Maria");
        ex.addParticipantes("Cenicienta");
        ex.addParticipantes("Bella");

        String frase = ex.toString();
        System.out.println(frase);

        frase = ex.toStringBuilder();
        System.out.println(frase);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        frase = gson.toJson(ex);
        System.out.println(frase);


    }
}

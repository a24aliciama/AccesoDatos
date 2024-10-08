package org.Flujos.ClasesDao;

import java.io.*;
import java.nio.file.*;
import java.util.Set;
import java.util.List;

public class ClasificacionFileDAO implements DAO<Clasificacion, String> {

    private final Path ruta;  // Ruta donde se guardan las clasificaciones

    // Constructor al que se le pasa la ruta del directorio donde se guardarán las clasificaciones
    public ClasificacionFileDAO(Path ruta) {
        this.ruta = ruta;
        if (!Files.exists(ruta)) {
            try {
                Files.createDirectories(ruta);
            } catch (IOException e) {
                System.out.println("Error creando el directorio: " + e.getMessage());
            }
        }
    }

    @Override
    public Clasificacion get(String nombreCompeticion) {
        Path archivo = ruta.resolve(nombreCompeticion + ".dat");
        if (Files.exists(archivo)) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo.toFile()))) {
                return (Clasificacion) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar la clasificación: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontró un archivo para la competición: " + nombreCompeticion);
        }
        return null;
    }

    @Override
    public List<Clasificacion> getAll() {
        // Este método podría implementarse si fuera necesario listar todas las clasificaciones guardadas
        return null;
    }

    @Override
    public boolean save(Clasificacion clasificacion) {
        // Guardar la clasificación
        Path archivo = ruta.resolve(clasificacion.getCompeticion() + ".dat");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo.toFile()))) {
            oos.writeObject(clasificacion);
            System.out.println("Clasificación guardada correctamente: " + archivo);
            return true;
        } catch (IOException e) {
            System.out.println("Error al guardar la clasificación: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Clasificacion clasificacion) {
        Path archivo = ruta.resolve(clasificacion.getCompeticion() + ".dat");
        try {
            return Files.deleteIfExists(archivo);
        } catch (IOException e) {
            System.out.println("Error al eliminar el archivo: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(ruta)) {
            for (Path archivo : directoryStream) {
                Files.delete(archivo);
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error al eliminar todos los archivos: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(String nombreCompeticion) {
        Path archivo = ruta.resolve(nombreCompeticion + ".dat");
        try {
            return Files.deleteIfExists(archivo);
        } catch (IOException e) {
            System.out.println("Error al eliminar el archivo: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void update(Clasificacion clasificacion) {
        save(clasificacion);  // Para actualizar se vuelve a guardar el archivo
    }
}

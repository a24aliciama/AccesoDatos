package org.Flujos.ClasesDao;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EquipoFileDAO implements DAO<Equipo, String> {
    private final Path path;

    public EquipoFileDAO(Path path) {
        this.path = path;
    }

    @Override
    public Equipo get(String nombre) {
        return getAll().stream()
                .filter(equipo -> equipo.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Equipo> getAll() {
        List<Equipo> equipos = new ArrayList<>();
        if (!Files.exists(path)) return equipos;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            equipos = (List<Equipo>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return equipos;
    }

    @Override
    public boolean save(Equipo equipo) {
        List<Equipo> equipos = getAll();
        equipos.add(equipo);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            oos.writeObject(equipos);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Equipo equipo) {
        List<Equipo> equipos = getAll();
        if (equipos.remove(equipo)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
                oos.writeObject(equipos);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        try {
            return Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteById(String nombre) {
        Equipo equipo = get(nombre);
        return equipo != null && delete(equipo);
    }

    @Override
    public void update(Equipo equipo) {
        deleteById(equipo.getNombre());
        save(equipo);
    }
}
package org.Flujos.ClasesDao;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

public class Clasificacion implements Serializable {
    private Set<Equipo> equipos;
    private String competicion;

    // Constructor por defecto con la competición "Liga ACB"
    public Clasificacion() {
        this("Liga ACB");
    }

    // Constructor con el nombre de la competición
    public Clasificacion(String competicion) {
        this.competicion = competicion;
        this.equipos = new TreeSet<>();
    }

    // Métod para añadir equipo
    public boolean addEquipo(Equipo equipo) {
        return equipos.add(equipo);
    }

    // Métod para eliminar equipo
    public boolean removeEquipo(Equipo equipo) {
        return equipos.remove(equipo);
    }

    public Set<Equipo> getEquipos() {
        return equipos;
    }

    public String getCompeticion() {
        return competicion;
    }

    // Sobrescribimos el métod toString para mostrar la clasificación
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Clasificación de la competición: ").append(competicion).append("\n");
        equipos.forEach(equipo -> sb.append(equipo).append("\n"));
        return sb.toString();
    }
}



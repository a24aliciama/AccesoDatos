package GSON.EjExamen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Examen {

    String materia;
    Date fecha;
    List<String> participantes = new ArrayList<>();

    public Examen(String materia, Date fecha) {
        this.materia = materia;
        this.fecha = fecha;
    }

    public String getMateria() {
        return materia;
    }

    public Date getFecha() {
        return fecha;
    }

    public List<String> getParticipantes() {
        return participantes;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }

    public void addParticipantes(String nombre){
        participantes.add(nombre);
    }

    @Override
    public String toString() {
        return "Examen{" +
                "materia='" + materia + '\'' +
                ", fecha=" + fecha +
                ", participantes=" + participantes +
                '}';
    }

    public String toStringBuilder(){
        StringBuilder sb = new StringBuilder();
        sb.append("Examen{");
                sb.append("materia='")
                        .append(materia)
                        .append("\'")
                        .append(", fecha=")
                        .append(fecha)
                        .append(", participantes= ")
                        .append(participantes)
                        .append("}");

                return sb.toString();
    }
}


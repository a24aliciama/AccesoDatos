package org.ClasesDao;
import java.io.Serializable;

public class Equipo implements Comparable<Equipo>, Serializable {
    private String nombre;
    private int victorias;
    private int derrotas;
    private int puntosAFavor;
    private int puntosEnContra;

    // Constructor mínimo
    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    // Constructor completo
    public Equipo(String nombre, int victorias, int derrotas, int puntosAFavor, int puntosEnContra) {
        this.nombre = nombre;
        this.victorias = victorias;
        this.derrotas = derrotas;
        this.puntosAFavor = puntosAFavor;
        this.puntosEnContra = puntosEnContra;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getPuntosAFavor() {
        return puntosAFavor;
    }

    public int getPuntosEnContra() {
        return puntosEnContra;
    }

    // Atributos derivados
    public int getPuntos() {
        return victorias - derrotas;
    }

    public int getPartidosJugados() {
        return victorias + derrotas;
    }

    public int getDiferenciaDePuntos() {
        return puntosAFavor - puntosEnContra;
    }

    // Implementación de Comparable (ordena por puntos, luego diferencia de puntos)
    @Override
    public int compareTo(Equipo otro) {
        int comparacionPuntos = Integer.compare(otro.getPuntos(), this.getPuntos());
        if (comparacionPuntos == 0) {
            return Integer.compare(otro.getDiferenciaDePuntos(), this.getDiferenciaDePuntos());
        }
        return comparacionPuntos;
    }

    // Métodos equals y hashCode basados en el nombre (sin distinguir mayúsculas/minúsculas)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Equipo equipo = (Equipo) obj;
        return nombre.equalsIgnoreCase(equipo.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s | Partidos: %d, Victorias: %d, Derrotas: %d, PuntosVictorias: %d, DiferenciaPuntos: %d",
                nombre, getPartidosJugados(), victorias, derrotas, getPuntos(), getDiferenciaDePuntos());
    }
}

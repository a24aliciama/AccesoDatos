package org.Boletin1_1;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.Scanner;

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

        // Apartado a:
        public static void apartadoA() {
            Scanner teclado = new Scanner(System.in);
            System.out.print("Archivo origen: ");
            String origen = teclado.nextLine();
            System.out.print("Archivo destino: ");
            String destino = teclado.nextLine();

            File origenFile = new File(origen);
            File destinoFile = new File(destino);

            if (!origenFile.exists()) {
                System.out.println("El origen no existe.");
                return;
            }

            if (destinoFile.exists()) {
                System.out.print("El archivo destino ya existe. ¿Deseas sobrescribirlo? (s/n): ");
                String resp = teclado.nextLine();
                if (!resp.equalsIgnoreCase("s")) {
                    System.out.println("Operación cancelada.");
                    return;
                }
            }

            try (FileInputStream in = new FileInputStream(origenFile);
                 FileOutputStream out = new FileOutputStream(destinoFile)) {
                int byteData;
                while ((byteData = in.read()) != -1) {
                    out.write(byteData);
                }
                System.out.println("Archivo copiado.");
            } catch (IOException e) {
                System.err.println("Error al copiar: " + e.getMessage());
            }
        }

        // Apartado b)
        public static void apartadoB() {
            JFileChooser buscador = new JFileChooser();
            buscador.setDialogTitle("Selecciona el archivo origen");

            if (buscador.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File origenFile = buscador.getSelectedFile();

                buscador.setDialogTitle("Selecciona el archivo destino");
                if (buscador.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File destinoFile = buscador.getSelectedFile();

                    if (destinoFile.exists()) {
                        int resp = JOptionPane.showConfirmDialog(null, "El archivo destino ya existe. ¿Deseas sobrescribirlo?", "Confirmación", JOptionPane.YES_NO_OPTION);
                        if (resp != JOptionPane.YES_OPTION) {
                            JOptionPane.showMessageDialog(null, "Operación cancelada.");
                            return;
                        }
                    }

                    try (FileInputStream in = new FileInputStream(origenFile);
                         FileOutputStream out = new FileOutputStream(destinoFile)) {
                        int byteData;
                        while ((byteData = in.read()) != -1) {
                            out.write(byteData);
                        }
                        JOptionPane.showMessageDialog(null, "Archivo copiado.");
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error al copiar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }

        // Apartado c)
        public static void apartadoC() {
            String url = JOptionPane.showInputDialog("Introduce la URL del archivo a descargar:");
            if (url == null || url.isEmpty()) {
                JOptionPane.showMessageDialog(null, "URL no válida.");
                return;
            }

            try (InputStream in = new URL(url).openStream()) {
                JFileChooser buscador = new JFileChooser();
                buscador.setDialogTitle("Guardar archivo como");

                if (buscador.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File destinoFile = buscador.getSelectedFile();

                    try (FileOutputStream out = new FileOutputStream(destinoFile)) {
                        int byteData;
                        while ((byteData = in.read()) != -1) {
                            out.write(byteData);
                        }
                        JOptionPane.showMessageDialog(null, "Archivo descargado exitosamente.");
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al descargar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    // Apartado d)
    public static void apartadoD() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Archivo origen: ");
        String sourcePath = scanner.nextLine();
        System.out.print("Archivo destino: ");
        String destPath = scanner.nextLine();

        File origenFile = new File(sourcePath);
        File destinoFile = new File(destPath);

        if (!origenFile.exists()) {
            System.out.println("El origen no existe.");
            return;
        }

        if (destinoFile.exists()) {
            System.out.print("El archivo destino ya existe. ¿Deseas sobrescribirlo? (s/n): ");
            String resp = scanner.nextLine();
            if (!resp.equalsIgnoreCase("s")) {
                System.out.println("Operación cancelada.");
                return;
            }
        }

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(origenFile));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destinoFile))) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            System.out.println("Archivo copiado exitosamente usando buffer.");
        } catch (IOException e) {
            System.err.println("Error al copiar el archivo: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
            String[] options = {"Copiar archivo con Scanner", "Copiar archivo con JFileChooser", "Descargar archivo desde URL","Copiar archivo con Scanner y Buffer", "Salir"};
            while (true) {
                int choice = JOptionPane.showOptionDialog(null, "Selecciona una opción", "Operaciones de Archivo", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                switch (choice) {
                    case 0:
                        apartadoA();
                        break;
                    case 1:
                        apartadoB();
                        break;
                    case 2:
                        apartadoC();
                        break;
                    case 3:
                        apartadoD();
                        break;
                    default:
                        System.exit(0);
                }
            }
    }
}


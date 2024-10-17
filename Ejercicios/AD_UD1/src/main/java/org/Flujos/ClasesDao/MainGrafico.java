package org.Flujos.ClasesDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

public class MainGrafico extends JFrame {
    private ClasificacionFileDAO clasificacionDAO;
    private Clasificacion clasificacion;

    /**
     * La clasificacion se sobreescribira si no cargas los datos y guardas
     */
    public MainGrafico() {
        clasificacionDAO = new ClasificacionFileDAO(Paths.get("./clasificaciones"));
        clasificacion = new Clasificacion("Liga ACB");

        // Configuración de la ventana principal
        setTitle("Clasificación de la Liga");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton btnAddTeam = new JButton("Añadir equipo");
        JButton btnShowClasificacion = new JButton("Mostrar clasificación");
        JButton btnSaveClasificacion = new JButton("Guardar clasificación");
        JButton btnLoadClasificacion = new JButton("Cargar clasificación");
        JButton btnExit = new JButton("Salir");

        panel.add(btnAddTeam);
        panel.add(btnShowClasificacion);
        panel.add(btnSaveClasificacion);
        panel.add(btnLoadClasificacion);
        panel.add(btnExit);

        add(panel, BorderLayout.CENTER);

        // Acción para "Añadir equipo"
        btnAddTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField nombreField = new JTextField();
                JTextField victoriasField = new JTextField();
                JTextField derrotasField = new JTextField();
                JTextField puntosAFavorField = new JTextField();
                JTextField puntosEnContraField = new JTextField();

                Object[] inputFields = {
                        "Nombre del equipo:", nombreField,
                        "Número de victorias:", victoriasField,
                        "Número de derrotas:", derrotasField,
                        "Puntos a favor:", puntosAFavorField,
                        "Puntos en contra:", puntosEnContraField
                };

                int option = JOptionPane.showConfirmDialog(null, inputFields, "Añadir equipo", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    String nombre = nombreField.getText();
                    int victorias = Integer.parseInt(victoriasField.getText());
                    int derrotas = Integer.parseInt(derrotasField.getText());
                    int puntosAFavor = Integer.parseInt(puntosAFavorField.getText());
                    int puntosEnContra = Integer.parseInt(puntosEnContraField.getText());

                    Equipo equipo = new Equipo(nombre, victorias, derrotas, puntosAFavor, puntosEnContra);
                    if (clasificacion.addEquipo(equipo)) {
                        JOptionPane.showMessageDialog(null, "Equipo añadido con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(null, "El equipo ya está en la clasificación.");
                    }
                }
            }
        });

        // Acción para "Mostrar clasificación"
        btnShowClasificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, clasificacion.toString(), "Clasificación actual", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Acción para "Guardar clasificación"
        btnSaveClasificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clasificacionDAO.save(clasificacion)) {
                    JOptionPane.showMessageDialog(null, "Clasificación guardada con éxito.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar la clasificación.");
                }
            }
        });

        // Acción para "Cargar clasificación"
        btnLoadClasificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clasificacion cargada = clasificacionDAO.get("Liga ACB");
                if (cargada != null) {
                    clasificacion = cargada;
                    JOptionPane.showMessageDialog(null, "Clasificación cargada con éxito.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al cargar la clasificación.");
                }
            }
        });

        // Acción para "Salir"
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Deseas guardar antes de salir?", "Salir", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (clasificacionDAO.save(clasificacion)) {
                        JOptionPane.showMessageDialog(null, "Clasificación guardada antes de salir.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al guardar la clasificación.");
                    }
                }
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainGrafico mainWindow = new MainGrafico();
                mainWindow.setVisible(true);
            }
        });
    }
}
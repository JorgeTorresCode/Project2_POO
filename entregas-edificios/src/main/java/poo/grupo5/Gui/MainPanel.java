package poo.grupo5.Gui;

import poo.grupo5.Logica.Control;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private Gui parentFrame; // Referencia al JFrame principal

    public MainPanel(Gui parentFrame, Control control) {
        this.parentFrame = parentFrame;

        setLayout(new BorderLayout(5, 5));

        add(new JLabel("<html><h1 style='color: blue;'>Gestión de Flotilla de Vehículos</h1></html>", SwingConstants.CENTER), BorderLayout.NORTH);

        JTextArea flotillaArea = new JTextArea(10, 30);
        flotillaArea.setEditable(false);
        add(new JScrollPane(flotillaArea), BorderLayout.CENTER);

        JButton backButton = new JButton("⬅️ Volver a Configuración");
        backButton.addActionListener(e -> {
            parentFrame.showPanel(Gui.DistanciasPanel);
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JTextField(15));
        controlPanel.add(new JButton("Agregar Vehículo"));
        controlPanel.add(backButton);

        add(controlPanel, BorderLayout.SOUTH);
    }
}
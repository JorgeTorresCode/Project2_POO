package poo.grupo5.Gui;

import poo.grupo5.Logica.Control;

import javax.swing.*;
import java.awt.*;

public class EdificiosPanel extends JPanel {
    private Gui parentFrame;
    private Control control;

    public EdificiosPanel(Gui parentFrame, Control control) {
        this.control = control;
        this.parentFrame = parentFrame;

        setLayout(new FlowLayout());
        add(new JLabel("Ingrese la cantidad inicial de edificios:"));

        JTextField edificioField = new JTextField(10);
        add(edificioField, BorderLayout.CENTER);

       JButton continuarButton = new JButton("Continuar");
        add(continuarButton, BorderLayout.CENTER);
        continuarButton.addActionListener(e -> {
            try {
                int cantidad = Integer.parseInt(edificioField.getText().trim());
                if (cantidad > 0) {
                    control.crearEdificios(cantidad);
                    control.crearCentroVehiculos();
                    parentFrame.showPanel("distanciasPanel");
                    parentFrame.showPanel(Gui.DistanciasPanel);
                } else {
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
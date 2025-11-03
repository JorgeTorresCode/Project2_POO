package poo.grupo5.Gui;

import poo.grupo5.Logica.Control;

import javax.swing.*;

public class ConfigPanel extends JPanel {
    private Gui parentFrame;
    private JTextField edificioField;
    private JButton continuarButton;

    public ConfigPanel(Gui parentFrame, Control control) {
        this.parentFrame = parentFrame;

        add(new JLabel("Ingrese la cantidad de edificios:"));

        edificioField = new JTextField(10);
        add(edificioField);

        continuarButton = new JButton("Continuar");
        continuarButton.addActionListener(e -> {
            try {
                int cantidad = Integer.parseInt(edificioField.getText().trim());
                if (cantidad > 0) {
                    control.crearEdificios(cantidad);
                    parentFrame.showPanel(Gui.VISTA_PRINCIPAL);
                } else {
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(continuarButton);
    }
}
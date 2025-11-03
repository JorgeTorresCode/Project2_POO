package poo.grupo5.Gui;

import poo.grupo5.Logica.Control;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {
    private Control control =  new Control();
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);

    public static final String VISTA_PRINCIPAL = "Principal";
    public static final String VISTA_CONFIGURACION = "Configuracion";

    public Gui() {
        setTitle("Gestión de la Aplicación");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ConfigPanel configPanel = new ConfigPanel(this, control);
        MainPanel mainPanel = new MainPanel(this, control);

        cardPanel.add(configPanel, VISTA_CONFIGURACION);
        cardPanel.add(mainPanel, VISTA_PRINCIPAL);

        add(cardPanel);

        showPanel(VISTA_CONFIGURACION);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void showPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Gui::new);
    }
}
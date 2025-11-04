package poo.grupo5.Gui;

import poo.grupo5.Logica.Control;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {
    private Control control =  new Control();
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);

    public static final String PrincipalPanel = "PrincipalPanel";
    public static final String EdificiosPanel = "EdificiosPanel";
    public static final String DistanciasPanel = "DistanciasPanel";

    public Gui() {
        setTitle("Gestión de la Aplicación");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EdificiosPanel edificiosPanel = new EdificiosPanel(this, control);
        MainPanel mainPanel = new MainPanel(this, control);
        DistanciasPanel distanciasPanel = new DistanciasPanel(this, control);

        cardPanel.add(edificiosPanel, EdificiosPanel);
        cardPanel.add(mainPanel, PrincipalPanel);
        cardPanel.add(distanciasPanel, DistanciasPanel);

        add(cardPanel);
        showPanel(EdificiosPanel);
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
package poo.grupo5.Gui;

import javax.swing.*;
import java.awt.*;
import poo.grupo5.Logica.Control;

public class GuiController extends JFrame {

    private Control control = new Control();
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);

    // 🔹 Referencias  paneles
    private MapaPanel mapaPanel;
    private ConfiguracionPanel configuracionPanel;
    private DistanciasPanel distanciasPanel;
    private VehiculosPanel vehiculoPanel;
    private RoversPanel roversPanel;
    private EBikesPanel eBikesPanel;

    public GuiController() {
        setTitle("Gestión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1650, 850);

        // Crear los paneles (les pasamos la misma lógica y el mismo controlador)
        configuracionPanel = new ConfiguracionPanel(this, control);
        distanciasPanel = new DistanciasPanel(this, control);
        vehiculoPanel = new  VehiculosPanel(this,control);
        roversPanel = new RoversPanel(this, control);
        eBikesPanel = new EBikesPanel(this,control);
        mapaPanel = new MapaPanel(this, control);

        // Agregarlos al contenedor con nombres únicos
        cardPanel.add(configuracionPanel, "ConfiguracionPanel");
        cardPanel.add(distanciasPanel, "DistanciasPanel");
        cardPanel.add(vehiculoPanel,"VehiculoPanel");
        cardPanel.add(roversPanel, "RoversPanel");
        cardPanel.add(eBikesPanel, "EBikesPanel");
        cardPanel.add(mapaPanel, "MapaPanel");

        add(cardPanel);
        setLocationRelativeTo(null);
        showPanel("ConfiguracionPanel");
        setVisible(true);
    }

    
    public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }

    
    public void mostrarDistanciasPanel() {
        distanciasPanel.prepararDatos();
        showPanel("DistanciasPanel");
    }
    
    public void showVehiculoPanel(){showPanel("VehiculoPanel");}
    
    public void showRoversPanel(){showPanel("RoversPanel");}
    
    public void showEBikesPanel(){showPanel("EBikesPanel");}
    
    public void showMapaPanel(){showPanel("MapaPanel");}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GuiController::new);
    }
}
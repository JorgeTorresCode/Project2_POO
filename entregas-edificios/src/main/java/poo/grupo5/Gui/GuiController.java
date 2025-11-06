package poo.grupo5.Gui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import poo.grupo5.Excepciones.MiExcepcion;
import poo.grupo5.Logica.Control;
import poo.grupo5.Modelo.Estructuras.Edificio;
import poo.grupo5.Modelo.Estructuras.Estructura;

public class GuiController extends JFrame {

    private Control control = new Control();
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);

    // 🔹 Referencias  paneles
    private MapaPanel mapaPanel;
    private ConfiguracionPanel configuracionPanel;
    private DistanciasPanel distanciasPanel;
    private DronesPanel vehiculoPanel;
    private RoversPanel roversPanel;
    private EBikesPanel eBikesPanel;

    public GuiController() {
        setTitle("Gestión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1650, 850);

        // Crear los paneles (les pasamos la misma lógica y el mismo controlador)
        
        configuracionPanel = new ConfiguracionPanel(this, control);
        distanciasPanel = new DistanciasPanel(this, control);
        vehiculoPanel = new  DronesPanel(this,control);
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

    public void showVehiculoPanel() {showPanel("VehiculoPanel");}
    public void showRoversPanel() {showPanel("RoversPanel");}
    public void showEBikesPanel() {showPanel("EBikesPanel");}
    public void showMapaPanel() {showPanel("MapaPanel");}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GuiController::new);
    }

    /*public static void main(String[] args) throws MiExcepcion {
        Control control1 = new Control();
        control1.crearCentroVehiculos();
        control1.crearEdificios(5);
        System.out.println(control1.mostrarEstructuras());

        control1.crearDrones(3,60,1,10);
        control1.crearRovers(4,100,10,5);
        control1.crearEBikes(5,30,5,10);

        System.out.println(control1.mostrarVehiculos());
        ArrayList<Edificio> edificios = (ArrayList<Edificio>) control1.consultarEdificios();
        LocalDateTime time = LocalDateTime.now();
        control1.generarPedido("primer pedido", 20, edificios.get(0), edificios.get(4), time);
        System.out.println(control1.mostrarPedidos());


    }*/
}
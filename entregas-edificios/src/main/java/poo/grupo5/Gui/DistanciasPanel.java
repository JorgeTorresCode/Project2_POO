/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.grupo5.Gui;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import poo.grupo5.Logica.Control;
import poo.grupo5.Modelo.Estructuras.Estructura;
import java.util.ArrayList;

public class DistanciasPanel extends JPanel {
    private GuiController guiController;
    private Control control;

    private ArrayList<Estructura> estructuras;
    private ArrayList<int[]> mapaPares;
    private Map<int[], JSlider> slidersMap;

    private JPanel contenido;
    private JButton botonGuardar;

    public DistanciasPanel(GuiController guiController, Control control) {
        this.guiController = guiController;
        this.control = control;

        inicializarUI();
        prepararDatos();
    }


    private void inicializarUI() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);


        contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JScrollPane scroll = new JScrollPane(contenido);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scroll, BorderLayout.CENTER);


        botonGuardar = new JButton("Guardar distancias");
        botonGuardar.addActionListener(e -> guardarRelaciones());
        add(botonGuardar, BorderLayout.SOUTH);
    }


    public void prepararDatos() {
        Collection<poo.grupo5.Modelo.Estructuras.Estructura> estTmp = control.consultarEstructuras();
        estructuras = new ArrayList<>(estTmp);
        mapaPares = new ArrayList<>();
        slidersMap = new HashMap<>();


        for (int i = 0; i < estructuras.size(); i++) {
            for (int j = i + 1; j < estructuras.size(); j++) {
                mapaPares.add(new int[]{i, j});
            }
        }
        generarSliders();
    }

    private void generarSliders() {
        contenido.removeAll();

        for (int[] par : mapaPares) {
            Estructura a = estructuras.get(par[0]);
            Estructura b = estructuras.get(par[1]);

            JPanel fila = new JPanel(new BorderLayout(10, 0));
            fila.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JLabel label = new JLabel(a.getId() + " ↔ " + b.getId());
            label.setFont(new Font("Arial", Font.BOLD, 14));
            fila.add(label, BorderLayout.WEST);

            JSlider slider = new JSlider(5, 500, 100);
            slider.setMajorTickSpacing(100);
            slider.setMinorTickSpacing(25);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);

            JLabel valorLabel = new JLabel("100 m");
            valorLabel.setFont(new Font("Arial", Font.PLAIN, 13));

            slider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    valorLabel.setText(slider.getValue() + " m");
                }
            });

            JPanel centro = new JPanel(new BorderLayout());
            centro.add(slider, BorderLayout.CENTER);
            centro.add(valorLabel, BorderLayout.EAST);

            fila.add(centro, BorderLayout.CENTER);
            contenido.add(fila);

            slidersMap.put(par, slider);
        }

        contenido.revalidate();
        contenido.repaint();
    }


    private void guardarRelaciones() {
        for (int[] par : mapaPares) {
            Estructura a = estructuras.get(par[0]);
            Estructura b = estructuras.get(par[1]);
            int valor = slidersMap.get(par).getValue();
            control.relacionarEstructuras(a.getId(), b.getId(), valor);
        }
        System.out.println(control.mostrarAristas());
        guiController.showVehiculoPanel();
    }
}

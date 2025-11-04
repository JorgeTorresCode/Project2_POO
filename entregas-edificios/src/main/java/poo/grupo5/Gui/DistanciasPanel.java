package poo.grupo5.Gui;

import poo.grupo5.Logica.Control;
import poo.grupo5.Modelo.Estructuras.Estructura;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class DistanciasPanel extends JPanel {
    private Gui parentFrame;
    private Control control;

    public DistanciasPanel(Gui parentFrame, Control control) {
        this.control = control;
        this.parentFrame = parentFrame;

        setLayout(new GridLayout());

        JButton randomButton = new JButton("Random distances");
        JPanel randomWrapper = new JPanel(new GridLayout());
        randomWrapper.setBorder(BorderFactory.createEmptyBorder(60, 20, 60, 20));
        randomWrapper.add(randomButton);
        add(randomWrapper);

        JButton setDistancesButton = new JButton("Set Distances");
        JPanel setDistancesWrapper = new JPanel(new GridLayout());
        setDistancesWrapper.setBorder(BorderFactory.createEmptyBorder(60, 20, 60, 20));
        setDistancesWrapper.add(setDistancesButton);
        add(setDistancesWrapper);

        setDistancesButton.addActionListener(e -> {
            graficarSeteoDistancias();
        });
        randomButton.addActionListener(e -> {
            setearDistanciasRandom();
        });
    }

    public void setearDistanciasRandom() {
        Collection<Estructura> estTmp = control.consultarEstructuras();
        ArrayList<Estructura> estructuras = new ArrayList<>(estTmp);
        for (int i = 0; i < estructuras.size(); i++) {
            Estructura estA = estructuras.get(i);
            for (int j = i + 1; j < estructuras.size(); j++) {
                Estructura estB = estructuras.get(j);
                control.relacionarEstructuras(estA.getId(), estB.getId(), (int) (5 + (Math.random() * (496))));
            }
        }
        parentFrame.showPanel(Gui.PrincipalPanel);
    }

    public void graficarSeteoDistancias() {
        removeAll();

        Collection<Estructura> estTmp = control.consultarEstructuras();
        ArrayList<Estructura> estructuras = new ArrayList<>(estTmp);
        ArrayList<JSlider> sliders = new ArrayList<>();

        JPanel estructurasWrapper = new JPanel(new GridLayout(0, 2, 5, 5));
        estructurasWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Font etiquetaFont = new Font("Arial", Font.BOLD, 14);

        for (int i = 0; i < estructuras.size(); i++) {
            Estructura estA = estructuras.get(i);
            for (int j = i + 1; j < estructuras.size(); j++) {
                Estructura estB = estructuras.get(j);

                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                JLabel etiquetaID = new JLabel(estA.getId() + "--" + estB.getId());
                etiquetaID.setFont(etiquetaFont);
                panel.add(etiquetaID);

                JSlider slider = new JSlider(JSlider.HORIZONTAL, 5, 500, 200);
                sliders.add(slider);
                slider.setPreferredSize(new Dimension(150, 20));
                panel.add(slider);

                JLabel etiquetaValor = new JLabel(" (" + slider.getValue() + " m)");
                etiquetaValor.setFont(etiquetaFont);
                panel.add(etiquetaValor);

                slider.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        etiquetaValor.setText(" (" + slider.getValue() + " m)");
                    }
                });
                estructurasWrapper.add(panel);
            }
        }
        JScrollPane scrollPane = new JScrollPane(estructurasWrapper);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        JButton aplicarButton = new JButton("Aplicar Distancias");
        JPanel botonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonWrapper.add(aplicarButton);
        add(botonWrapper, BorderLayout.SOUTH);

        aplicarButton.addActionListener(e -> {
            int sliderIndex = 0;
            for (int i = 0; i < estructuras.size(); i++) {
                Estructura estA = estructuras.get(i);
                for (int j = i + 1; j < estructuras.size(); j++) {
                    Estructura estB = estructuras.get(j);
                    control.relacionarEstructuras(estA.getId(), estB.getId(), sliders.get(sliderIndex).getValue());
                    sliderIndex++;
                }
            }
            parentFrame.showPanel(Gui.PrincipalPanel);
        });

        parentFrame.pack();
        parentFrame.setSize(parentFrame.getWidth() + 20, parentFrame.getHeight());
        repaint();
    }
}
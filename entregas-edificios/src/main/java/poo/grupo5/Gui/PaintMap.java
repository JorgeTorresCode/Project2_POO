/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poo.grupo5.Gui;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
/**
 *
 * @author jorgi
 */

public class PaintMap extends JPanel {
    private ArrayList<Point> circles = new ArrayList<>();
    private final int NODE_SIZE = 50;

    
    // Constructor de la clase
    public PaintMap() {
        setOpaque(true);
        setPreferredSize(new Dimension(1401, 868));
        setBackground(new java.awt.Color(255, 255, 255));
    }

    /**
     * Este es el método clave para dibujar. Se llama automáticamente.
     */
    
    public void drawCircle(int x, int y, int size, Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillOval(x, y, size, size);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2d = (Graphics2D) g;

        for (Point p : circles) {
            drawCircle(p.x, p.y, NODE_SIZE, g2d);
        }
    }
    

    public void addCircle() {
        int totalCircles = circles.size() + 1;
        ArrayList<Point> newPoints = new ArrayList<>();

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = 280;
        if (totalCircles == 1) {
            newPoints.add(new Point(centerX - NODE_SIZE/2, centerY - NODE_SIZE/2));
        } else {
            newPoints.add(new Point(centerX - NODE_SIZE/2, centerY - NODE_SIZE/2));
            for (int i = 1; i < totalCircles; i++) {
                double angle = 2 * Math.PI * (i - 1) / (totalCircles - 1);
                int x = centerX + (int)(radius * Math.cos(angle));
                int y = centerY + (int)(radius * Math.sin(angle));
                newPoints.add(new Point(x - NODE_SIZE/2, y - NODE_SIZE/2));
            }
        }
        circles = newPoints;
        repaint();
    }
}
package poo.jorgecarlosanthony.Gui;

import javax.swing.*;

public class Gui extends JFrame {
    private JPanel mainPanel;
    private JButton button1;

    public Gui() {
        setContentPane(mainPanel);
        setTitle("Mi Aplicación de GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        button1.addActionListener(e -> {
            SecondWindow secondWindow = new SecondWindow();
            secondWindow.setVisible(true);
        });
    }

    public static void main(String[] args) {
        Gui gui = new Gui();
    }
}
package poo.jorgecarlosanthony.Gui;

import javax.swing.*;

public class SecondWindow extends JFrame {
    private JPanel mainPanel;
    private JButton button1;
    private JTextPane textPane1;

    public SecondWindow() {
        setContentPane(mainPanel);
        setTitle("Mi Aplicación de GUI");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        button1.addActionListener(e -> {
            textPane1.setText("sdddd");
        });
    }
}

package exo1;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {

    public Fenetre(int nbButtons) {
        super("Une fenêtre"); // ou setTitle("...");
        setSize(1920, 1080); // ou setBounds(...);

        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());

        for (int i = 0; i < nbButtons; i++) {
            JButton button = new JButton("Button " + (i+1));
            contentPane.add(button);
        }
        setVisible(true); // ou show(), // affiche la fenêtre
    }
}
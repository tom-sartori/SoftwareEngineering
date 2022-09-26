package exo4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("agtaegzagzgzagze");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                try{
                    System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
                }catch (Exception exception){
                    System.out.println("la");
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("hhfhfdhfdfh");
            }
        });


        setVisible(true); // ou show(), // affiche la fenêtre
    }
}
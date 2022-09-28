package exo4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Fenetre extends JFrame implements ActionListener {

    public Fenetre(int nbButtons) {
        super("Une fenêtre"); // ou setTitle("...");
        setSize(1920, 1080); // ou setBounds(...);

        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());

        for (int i = 0; i < nbButtons; i++) {
            JButton button = new JButton("Button " + (i+1));
            contentPane.add(button);
            button.addActionListener(this);
        }

        ;

//        this.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                System.out.println("agtaegzagzgzagze");
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                try{
//                    System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
//                }catch (Exception exception){
//                    System.out.println("la");
//                }
//
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//                System.out.println("hhfhfdhfdfh");
//            }
//        });


        setVisible(true); // ou show(), // affiche la fenêtre
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("lala");
        Object source = e.getSource();
        System.out.println(source);
//        if (source == b1) { //Suppose que l'on est dans la portée //de b1 qui contient une référence au // bouton // action liée au bouton b1
//        }
//        else if (source == b2) { // action liée au bouton b2
//        }
//        else if (source == f) { // action liée au JTextField f
//        }

    }
}
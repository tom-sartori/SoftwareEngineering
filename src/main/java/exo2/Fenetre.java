package exo2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {

    private String nom;
    private String prenom;
    private String tel;

    public Fenetre() {
        super("Une fenêtre"); // ou setTitle("...");
        setSize(1920, 1080); // ou setBounds(...);

        Container contentPane = getContentPane();

        JPanel panelFirstName = new JPanel();

        JTextField fistNameField = new JTextField(15);
        panelFirstName.add(new JLabel("First name"));
        panelFirstName.add(fistNameField);

        JPanel panelLastName = new JPanel();

        JTextField lastNameField = new JTextField(15);
        panelLastName.add(new JLabel("Last name"));
        panelLastName.add(lastNameField);

        JPanel panelPhone = new JPanel();

        JTextField phoneNameField = new JTextField(15);
        panelPhone.add(new JLabel("Telephone"));
        panelPhone.add(phoneNameField);


        JPanel topForm = new JPanel();
        topForm.setLayout(new GridLayout(3, 2));
        topForm.add(panelFirstName);
        topForm.add(panelLastName);
        topForm.add(panelPhone);


        contentPane.add(topForm, BorderLayout.NORTH);


        JTextArea textArea = new JTextArea();
        contentPane.add(textArea, BorderLayout.CENTER);  // Text area middle.

        JPanel southPanel = new JPanel();
        JButton resumeButton = new JButton("Resumé");
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("Prénom: " + fistNameField.getText() + "\n" +
                                "Nom: " + lastNameField.getText() +  "\n" +
                                "Numéro de téléphone: " + phoneNameField.getText());
            }
        });

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        southPanel.add(resumeButton);
        southPanel.add(quitButton);

        contentPane.add(southPanel, BorderLayout.SOUTH);

        setVisible(true); // ou show(), // affiche la fenêtre
    }
}
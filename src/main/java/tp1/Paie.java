package tp1;

import tp1.toto.entreprise.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Paie {

    public static void main(String[] args) {
        Entreprise entreprise = new Entreprise(2);

        Commercial commercial = new Commercial("John", 15000, 1000);

        try {
            entreprise.addEmploye(new EmployeHoraire("Bob", 30, 1.3, 10));

            entreprise.addEmploye(commercial);

            EmployeHoraire e1 = new EmployeHoraire("Alice");
            e1.setInfoSalaire(45, 1.5, 10);
            entreprise.addEmploye(e1);

            entreprise.addEmploye(new Commercial("Jane", 1000, 1000));
            entreprise.addEmploye(new Commercial("Jane", 1000, 1000));
        } catch (EntrepriseSatureDeCommerciauxException e) {
            e.printStackTrace();
        }

        Directeur directeur = Directeur.createDirecteur("Directeur", 10000, entreprise);
        System.out.println(directeur);


        try {
            commercial.saveToFile(new FileWriter("commercial.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

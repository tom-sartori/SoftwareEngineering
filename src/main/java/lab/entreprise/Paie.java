package lab.entreprise;

import lab.entreprise.comparator.ORDER;
import lab.entreprise.comparator.SalaryComparator;
import lab.entreprise.employe.Commercial;
import lab.entreprise.employe.Directeur;
import lab.entreprise.employe.Employe;
import lab.entreprise.employe.EmployeHoraire;
import lab.entreprise.exception.EntrepriseSatureDeCommerciauxException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class Paie {

    public static void main(String[] args) {

        // First enterprise.
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
        Collections.sort(entreprise.getEmployeList());
        System.out.println(directeur);

        System.out.println("Total salaire entreprise 0: " + calculSumSalaries(entreprise));

        entreprise.getEmployeList().sort(new SalaryComparator(ORDER.DESC));
        System.out.println(entreprise);


        System.out.println("------------------------------------------");


        // Second enterprise.
        Entreprise entreprise1 = new Entreprise(10);
        try {
            entreprise1.addEmploye(new EmployeHoraire("Jean", 30, 1.3, 10));
            entreprise1.addEmploye(new Commercial("Jeannot", 15000, 1000));
        } catch (EntrepriseSatureDeCommerciauxException e) {
            throw new RuntimeException(e);
        }
        Collections.sort(entreprise1.getEmployeList());
        System.out.println(entreprise1);

        System.out.println("Total salaire entreprise 1: " + calculSumSalaries(entreprise1));


        entreprise1.getEmployeList().sort(new SalaryComparator());
        System.out.println(entreprise1);


        System.out.println("------------------------------------------");


        List<Employe> bothEmployeList = new ArrayList<>();
        bothEmployeList.addAll(entreprise.getEmployeList());
        bothEmployeList.addAll(entreprise1.getEmployeList());
        Collections.sort(bothEmployeList);
        System.out.println(bothEmployeList);
    }

    public static double calculSumSalaries(Entreprise entreprise) {
        double totalEntreprise = 0;
        Iterator<Employe> employeIterator = entreprise.iterEmployeList();
        while (employeIterator.hasNext()) {
            totalEntreprise += employeIterator.next().getSalaire();
        }
        return totalEntreprise;
    }

    public static void lab2InOut(Commercial commercial) {
        try {
            commercial.saveToFile(new FileWriter("commercial.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            commercial.readFromFile(new FileReader(new File("commercial.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void lab2InOutBinary(Commercial commercial) {
        try{
            DataOutputStream out = new DataOutputStream( new BufferedOutputStream( new FileOutputStream("bin.txt") ) );
            commercial.saveToFileBinary(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try{
            DataInputStream in5 = new DataInputStream( new BufferedInputStream( new FileInputStream("bin.txt")));
            commercial.readFromFileBinary(in5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

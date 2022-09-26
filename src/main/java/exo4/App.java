package exo4;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrez le nombre de bouton voulu : ");

        new Fenetre(sc.nextInt());
    }
}


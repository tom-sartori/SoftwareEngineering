package exercice1;

public class App {

    public static void main(String[] args) {

        //System.out.println("Hello World!");

        Compteur c1 = new Compteur("Toto", 10);

        c1.start();

        Compteur c2 = new Compteur("Titi", 10);

        c2.start();

        Compteur c3 = new Compteur("Alexis", 10);

        c3.start();

        Compteur c4 = new Compteur("Fondard", 10);

        c4.start();
    }
}

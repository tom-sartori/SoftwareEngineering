package exercice1;

import java.util.Random;

public class Compteur extends Thread {

    private String nom;
    private int max;

    public Compteur(String nom, int max) {
        this.nom = nom;
        this.max = max;
    }


    @Override
    public void run() {
        int i = 0;
        Random ran = new Random();

        while(i<=max){
            try {
                sleep(ran.nextInt(0, 5000));
                System.out.println(this.nom + " : " + i);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(this.nom + " a fini de compter jusqu'à :" + this.max);
    }
}

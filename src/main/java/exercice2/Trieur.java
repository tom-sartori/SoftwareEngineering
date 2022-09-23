package exercice2;

import java.util.Arrays;

/**
 * Tri d'un tableau d'entiers
 * Version mono-thread
 */
public class Trieur extends Thread{

    private int[] t;

    private int debut;
    private int fin;

    private Trieur(int[] t) {
        this.t = t;
        this.debut = 0;
        this.fin = t.length-1;
    }

    private Trieur(int[] t,int debut, int fin) {
        this.t = t;
        this.debut = debut;
        this.fin = fin;
    }

    /**
     * Trie un tableau d'entiers par ordre croissant
     * @param t tableau � trier
     */
    public static void trier(int[] t) {
        Trieur tableau = new Trieur(t);
        tableau.start();
        try {
            tableau.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        tableau.trier(0, t.length - 1);
    }

    /**
     * Trie une tranche de t
     * @param debut indice du d�but de la partie � trier
     * @param debut indice de la fin de la partie � trier
     */
    private void trier(int debut, int fin) {
        if (fin - debut < 2) {
            if (t[debut] > t[fin]) {
                echanger(debut, fin);
            }
        }
        else {
            int milieu = debut + (fin - debut) / 2;
            trier(debut, milieu);
            trier(milieu + 1, fin);
            triFusion(debut, fin);
        }
    }

    /**
     * Echanger t[i] et t[j]
     */
    private void echanger(int i, int j) {
        int valeur = t[i];
        t[i] = t[j];
        t[j] = valeur;
    }

    /**
     * Fusionne 2 tranches d�j� tri�es du tableau t.
     *   - 1�re tranche : de debut � milieu
     *   - 2�me tranche : de milieu + 1 � fin
     * @param debut indique le dernier indice de la 1�re tranche
     */
    private void triFusion(int debut, int fin) {
        // tableau o� va aller la fusion
        int[] tFusion = new int[fin - debut + 1];
        int milieu = (debut + fin) / 2;
        // Indices des �l�ments � comparer
        int i1 = debut,
                i2 = milieu + 1;
        // indice de la prochaine case du tableau tFusion � remplir
        int iFusion = 0;
        while (i1 <= milieu && i2 <= fin) {
            if (t[i1] < t[i2]) {
                tFusion[iFusion++] = t[i1++];
            }
            else {
                tFusion[iFusion++] = t[i2++];
            }
        }
        if (i1 > milieu) {
            // la 1�re tranche est �puis�e
            for (int i = i2; i <= fin; ) {
                tFusion[iFusion++] = t[i++];
            }
        }
        else {
            // la 2�me tranche est �puis�e
            for (int i = i1; i <= milieu; ) {
                tFusion[iFusion++] = t[i++];
            }
        }
        // Copie tFusion dans t
        for (int i = 0, j = debut; i <= fin - debut; ) {
            t[j++] = tFusion[i++];
        }
    }

    @Override
    public void run() {
        if (fin - debut < 2) {
            if (t[debut] > t[fin]) {
                echanger(debut, fin);
            }
        }
        else {
            int milieu = debut + (fin - debut) / 2;
            Trieur trieurfils1 = new Trieur(this.t,debut,milieu);
            Trieur trieurfils2 = new Trieur(this.t,milieu+1,fin);
            trieurfils1.start();
            trieurfils2.start();
            try {
                trieurfils1.join();
                trieurfils2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            trier(debut, milieu);
//            trier(milieu + 1, fin);
            triFusion(debut, fin);
        }
    }

    public static void main(String[] args) {
        int[] t = {5, 8, 3, 2, 7, 10, 1};
        Trieur.trier(t);
        for (int i = 0; i <t.length; i++) {
            System.out.print(t[i] + " ; ");
        }
        System.out.println();
    }
}


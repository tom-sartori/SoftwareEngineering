package exercise2;

import java.util.Arrays;

/**
 * Tri d'un tableau d'entiers
 * Version mono-thread
 */
public class TrieurThreaded extends Thread {

    private final int[] t;
    private int debut;
    private int fin;

    private TrieurThreaded(int[] t) {
        this.t = t;
        debut = 0;
        fin = t.length - 1;
    }

    private TrieurThreaded(int[] t, int debut, int fin) {
        this.t = t;
        this.debut = debut;
        this.fin = fin;
    }

    /**
     * Trie un tableau d'entiers par ordre croissant
     * @param t tableau � trier
     */
    public static void trier(int[] t) {
        try {

            TrieurThreaded trieur = new TrieurThreaded(t);
            trieur.start();
            trieur.join();

        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
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
            try {

                int milieu = debut + (fin - debut) / 2;

                TrieurThreaded child1 = new TrieurThreaded(t, debut, milieu);
                TrieurThreaded child2 = new TrieurThreaded(t, milieu + 1, fin);

                child1.start();
                child2.start();

                child1.join();
                child2.join();

                triFusion(debut, fin);

            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
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
}

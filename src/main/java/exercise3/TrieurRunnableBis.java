package exercise3;

/**
 * Tri d'un tableau d'entiers
 * Version mono-thread
 */
public class TrieurRunnableBis implements Runnable {

    private final int[] t;
    private int debut;
    private int fin;

    private TrieurRunnableBis(int[] t) {
        this.t = t;
        debut = 0;
        fin = t.length - 1;
    }

    private TrieurRunnableBis(int[] t, int debut, int fin) {
        this.t = t;
        this.debut = debut;
        this.fin = fin;
    }

    /**
     * Trie un tableau d'entiers par ordre croissant
     * @param t tableau � trier
     */
    public static void trier(int[] t) {
        TrieurRunnableBis trieurRunnableBis = new TrieurRunnableBis(t);
        trieurRunnableBis.run();
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

            TrieurRunnableBis child1 = new TrieurRunnableBis(t, debut, milieu);
            TrieurRunnableBis child2 = new TrieurRunnableBis(t, milieu + 1, fin);

            child1.run();
            child2.run();

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

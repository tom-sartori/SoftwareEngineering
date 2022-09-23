package exercise4;

/**
 * Tri d'un tableau d'entiers
 * Version mono-thread
 */
public class TrieurRunnable implements Runnable {

    private final int[] t;
    private final int debut;
    private final int fin;
    private final TrieurRunnable father;
    private int count;

    private TrieurRunnable(int[] t) {
        this.t = t;
        debut = 0;
        fin = t.length - 1;
        father = null;
    }

    private TrieurRunnable(int[] t, int debut, int fin, TrieurRunnable father) {
        this.t = t;
        this.debut = debut;
        this.fin = fin;
        this.father = father;
    }

    /**
     * Trie un tableau d'entiers par ordre croissant
     * @param t tableau � trier
     */
    public static void trier(int[] t) {
        try {

            Thread thread = new Thread(new TrieurRunnable(t));
            thread.start();

            thread.join();

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
                int milieu = debut + (fin - debut) / 2;

                Thread child1 = new Thread(new TrieurRunnable(t, debut, milieu, this));
                Thread child2 = new Thread(new TrieurRunnable(t, milieu + 1, fin, this));

                child1.start();
                child2.start();

                synchronized (this) {
                    while (count < 2) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                triFusion(debut, fin);
        }
        if (father != null) {
            synchronized (father) {
                father.count++;
                father.notifyAll();
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

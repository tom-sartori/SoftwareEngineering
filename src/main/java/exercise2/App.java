package exercise2;

public class App {

    public static void main(String[] args) {
        int[] t = { 5, 8, 3, 2, 7, 10, 1 };

//        Trieur.trier(t);          // Mono thread.
        TrieurThreaded.trier(t);    // Threaded.

        for (int i : t) {
            System.out.print(i + " ; ");
        }
        System.out.println();
    }
}

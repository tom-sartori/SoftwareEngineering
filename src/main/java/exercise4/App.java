package exercise4;

public class App {

    public static void main(String[] args) {
        int[] t = { 5, 8, 3, 2, 7, 10, 1 };

        TrieurRunnable.trier(t);

        for (int i : t) {
            System.out.print(i + " ; ");
        }
        System.out.println();
    }
}

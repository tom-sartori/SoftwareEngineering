package exercise1;

public class App {

    public static void main(String[] args) {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();
        Counter counter3 = new Counter();

        counter1.start();
        counter2.start();
        counter3.start();

        System.out.println("Main thread has finished. ");
    }
}

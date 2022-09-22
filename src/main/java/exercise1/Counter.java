package exercise1;

import java.util.Random;

public class Counter extends Thread {

    private final static Random random = new Random();

    @Override
    public void run() {
        try {

            for (int i = 0; i < 10; i++) {
                sleep(random.nextInt(0, 1000));
            }
            System.out.println(this.getName() + " has finished. ");

        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

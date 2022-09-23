package exercice1;

import java.util.Random;

public class CountBis {

    private final static Random ran = new Random();

    public static void main(String[] args) {
        Thread t1 = new CountThreaded();
        Thread t2 = new CountThreaded();
        Thread t3 = new CountThreaded();

        t1.start();
        t2.start();
        t3.start();

        System.out.println("Main finished. ");
    }

    public static class CountThreaded extends Thread {
        public void run() {
            int count = 0;
            for (int i = 0; i < 10; i++) {
                try {
                    sleep(ran.nextInt(0, 5000));
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(this.getName() + " has finished. " + count);
        }
    }
}

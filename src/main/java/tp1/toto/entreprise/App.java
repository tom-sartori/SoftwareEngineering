package tp1.toto.entreprise;

import tp1.Singleton;

public class App {

    public static void main(String[] args) {
        Singleton singleton = Singleton.createSingleton();
        Singleton singleton2 = Singleton.createSingleton();

        System.out.println(singleton == singleton2);
    }
}

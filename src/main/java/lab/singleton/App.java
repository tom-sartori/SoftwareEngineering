package lab.singleton;

public class App {

    public static void main(String[] args) {
        Singleton singleton = Singleton.createSingleton();
        Singleton singleton2 = Singleton.createSingleton();

        System.out.println(singleton == singleton2);
    }
}

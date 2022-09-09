package tp1;

public class Singleton {

    private static Singleton instance = null;

    private Singleton() {
    }

    public static Singleton createSingleton() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

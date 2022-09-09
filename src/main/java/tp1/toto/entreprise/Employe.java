package tp1.toto.entreprise;

public abstract class Employe {

    private final String nom;

    public Employe(String nom) {
        this.nom = nom;
    }

    public abstract double getSalaire();

    @Override
    public String toString() {
        return nom + " gagne " + getSalaire() + " €.";
    }
}

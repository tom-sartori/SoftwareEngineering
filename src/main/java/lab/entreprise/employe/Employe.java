package lab.entreprise.employe;

public abstract class Employe implements Comparable<Employe> {

    private final String nom;

    public Employe(String nom) {
        this.nom = nom;
    }

    public abstract double getSalaire();

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom + " gagne " + getSalaire() + " €.";
    }

    @Override
    public int compareTo(Employe o) {
        return nom.compareTo(o.nom);
    }
}

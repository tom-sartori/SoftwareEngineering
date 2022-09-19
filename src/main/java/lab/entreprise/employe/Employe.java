package lab.entreprise.employe;

public abstract class Employe implements Comparable<Employe> {

    private final String nom;

    private int order;
    private final static int ASC_ORDER = 1;
    private final static int DESC_ORDER = -1;

    public Employe(String nom) {
        this.order = ASC_ORDER;
        this.nom = nom;
    }

    public void setAscOrder() {
        this.order = ASC_ORDER;
    }

    public void setDescOrder() {
        this.order = DESC_ORDER;
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
        return order * nom.compareTo(o.nom);
    }
}

package lab.entreprise.employe;

import lab.entreprise.comparator.ORDER;

public abstract class Employe implements Comparable<Employe> {

    private final String nom;

    private ORDER order;

    public Employe(String nom) {
        this.order = ORDER.ASC;
        this.nom = nom;
    }

    public void setAscOrder() {
        this.order = ORDER.ASC;
    }

    public void setDescOrder() {
        this.order = ORDER.DESC;
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
        return order.getValue() * nom.compareTo(o.nom);
    }
}

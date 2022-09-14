package lab.entreprise.employe;


import lab.entreprise.Entreprise;

public class Directeur extends Employe {

    private static Directeur instance = null;
    private double sommeFixe;
    private Entreprise entreprise;

    private Directeur(String nom) {
        super(nom);
        this.entreprise = null;
    }

    private Directeur(String nom, double sommeFixe, Entreprise entreprise) {
        super(nom);
        this.sommeFixe = sommeFixe;
        this.entreprise = entreprise;
    }

    @Override
    public double getSalaire() {
        double totalChiffreAffaires = entreprise.getEmployes().stream()
                .filter(e -> e instanceof Commercial)
                .mapToDouble(e -> ((Commercial) e).getChiffreAffaires())
                .sum();

        return (totalChiffreAffaires * 0.004) + sommeFixe;
    }

    public static Directeur createDirecteur(String nom) {
        if (instance == null) {
            instance = new Directeur(nom);
        }
        return instance;
    }

    public static Directeur createDirecteur(String nom, double sommeFixe, Entreprise entreprise) {
        if (instance == null) {
            instance = new Directeur(nom, sommeFixe, entreprise);
        }
        return instance;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    @Override
    public String toString() {
        return "Le directeur gagne : " + getSalaire() + " €. \n" +
                "Son entreprise=" + entreprise +
                '}';
    }
}

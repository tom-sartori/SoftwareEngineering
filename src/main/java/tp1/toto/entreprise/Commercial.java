package tp1.toto.entreprise;

public class Commercial extends Employe {

    private double chiffreAffaires;
    private double sommeFixe;

    public Commercial(String nom) {
        super(nom);
    }

    public Commercial(String nom, double chiffreAffaires, double sommeFixe) {
        super(nom);
        this.chiffreAffaires = chiffreAffaires;
        this.sommeFixe = sommeFixe;
    }

    @Override
    public double getSalaire() {
        return sommeFixe + (chiffreAffaires * 0.01);
    }

    public void setInfoSalaire(double chiffreAffaires, double sommeFixe) {
        this.chiffreAffaires = chiffreAffaires;
        this.sommeFixe = sommeFixe;
    }

    public double getChiffreAffaires() {
        return chiffreAffaires;
    }
}

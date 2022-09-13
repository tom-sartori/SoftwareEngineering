package tp1.toto.entreprise;

import java.io.FileWriter;

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

    public void saveToFile(FileWriter fileWriter) {
        try {
            fileWriter.write(this.getNom() + "|" + this.chiffreAffaires + "|" + this.sommeFixe + "|" + this.getSalaire());
            fileWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public double getChiffreAffaires() {
        return chiffreAffaires;
    }
}

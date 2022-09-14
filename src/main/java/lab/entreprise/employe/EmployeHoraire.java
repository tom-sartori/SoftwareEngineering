package lab.entreprise.employe;

import lab.entreprise.employe.Employe;

public class EmployeHoraire extends Employe {

    private double nbHeureTravail;
    private double coefSupp;
    private double salaireHoraire;

    public EmployeHoraire(String nom) {
        super(nom);
    }

    public EmployeHoraire(String nom, double nbHeureTravail, double coefSupp, double salaireHoraire) {
        super(nom);
        this.nbHeureTravail = nbHeureTravail;
        this.coefSupp = coefSupp;
        this.salaireHoraire = salaireHoraire;
    }

    public double getSalaire() {
        double nbHeureSupp = nbHeureTravail - 35;

        if (nbHeureSupp < 0) {
            nbHeureSupp = 0;
        }

        return (nbHeureSupp * coefSupp * salaireHoraire) + ( (nbHeureTravail - nbHeureSupp) * salaireHoraire );
    }

    public void setInfoSalaire(double nbHeureTravail, double coefSupp, double salaireHoraire) {
        this.nbHeureTravail = nbHeureTravail;
        this.coefSupp = coefSupp;
        this.salaireHoraire = salaireHoraire;
    }
}

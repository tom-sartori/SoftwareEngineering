package lab.entreprise;

import lab.entreprise.employe.Commercial;
import lab.entreprise.employe.Employe;
import lab.entreprise.exception.EntrepriseSatureDeCommerciauxException;

import java.util.ArrayList;
import java.util.List;

public class Entreprise {
    private List<Employe> employes;
    private final int nbMaxCommerciaux;
    private int nbCommerciaux;


    public Entreprise(int nbMaxCommerciaux) {
        this.nbMaxCommerciaux = nbMaxCommerciaux;
        this.employes = new ArrayList<>();
        this.nbCommerciaux=0;
    }

    public void addEmploye(Employe employe) throws EntrepriseSatureDeCommerciauxException {
        if(employe instanceof Commercial) {
            if (nbCommerciaux < nbMaxCommerciaux) {
                employes.add(employe);
                nbCommerciaux++;
            }
            else{
                throw new EntrepriseSatureDeCommerciauxException(this);
            }
        }else{
            employes.add(employe);
        }
    }

    public int getNbMaxCommerciaux() {
        return nbMaxCommerciaux;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    @Override
    public String toString() {
        return "Entreprise{" +
                "employes=" + employes +
                ", nbMaxCommerciaux=" + nbMaxCommerciaux +
                ", nbCommerciaux=" + nbCommerciaux +
                '}';
    }
}

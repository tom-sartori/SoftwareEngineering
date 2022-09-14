package lab.entreprise.exception;

import lab.entreprise.Entreprise;

public class EntrepriseSatureDeCommerciauxException extends Exception {

    private Entreprise entreprise;
    public EntrepriseSatureDeCommerciauxException(Entreprise entreprise) {
        super("Impossible d'ajouter un comercial à cette entreprise car elle ne peut contenir que " + entreprise.getNbMaxCommerciaux() + " commerciaux. ");
        this.entreprise = entreprise;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }
}

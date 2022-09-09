package tp1;

import tp1.toto.entreprise.*;


public class Paie {

    public static void main(String[] args) {
        Entreprise entreprise = new Entreprise(2);

        try {
            entreprise.addEmploye(new EmployeHoraire("Bob", 30, 1.3, 10));

            entreprise.addEmploye(new Commercial("John", 1000, 1000));

            EmployeHoraire e1 = new EmployeHoraire("Alice");
            e1.setInfoSalaire(45, 1.5, 10);
            entreprise.addEmploye(e1);

            entreprise.addEmploye(new Commercial("Jane", 1000, 1000));
            entreprise.addEmploye(new Commercial("Jane", 1000, 1000));
        } catch (EntrepriseSatureDeCommerciauxException e) {
            e.printStackTrace();
        }

        Directeur directeur = Directeur.createDirecteur("Directeur", 10000, entreprise);
        System.out.println(directeur);
    }
}

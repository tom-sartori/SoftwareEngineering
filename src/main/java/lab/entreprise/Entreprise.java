package lab.entreprise;

import lab.entreprise.comparator.SalaryComparator;
import lab.entreprise.employe.Commercial;
import lab.entreprise.employe.Employe;
import lab.entreprise.exception.EntrepriseSatureDeCommerciauxException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Entreprise {
    private List<Employe> employeList;
    private final int nbMaxCommerciaux;
    private int nbCommerciaux;


    public Entreprise(int nbMaxCommerciaux) {
        this.nbMaxCommerciaux = nbMaxCommerciaux;
        this.employeList = new ArrayList<>();
        this.nbCommerciaux = 0;
    }

    public void addEmploye(Employe employe) throws EntrepriseSatureDeCommerciauxException {
        if(employe instanceof Commercial) {
            if (nbCommerciaux < nbMaxCommerciaux) {
                employeList.add(employe);
                nbCommerciaux++;
            }
            else{
                throw new EntrepriseSatureDeCommerciauxException(this);
            }
        }else{
            employeList.add(employe);
        }
    }

    public void removeEmploye(Employe employe) {
        if(employe instanceof Commercial) {
            nbCommerciaux--;
        }
        employeList.remove(employe);
    }

    public Iterator<Employe> iterEmployeList() {
        return employeList.iterator();
    }

    public int getNbMaxCommerciaux() {
        return nbMaxCommerciaux;
    }

    public List<Employe> getEmployeList() {
        return employeList;
    }

    public void setEmployeList(List<Employe> employeList) {
        this.employeList = employeList;
    }

    @Override
    public String toString() {
        return "Entreprise{" +
                "employes=" + employeList +
                ", nbMaxCommerciaux=" + nbMaxCommerciaux +
                ", nbCommerciaux=" + nbCommerciaux +
                '}';
    }
}

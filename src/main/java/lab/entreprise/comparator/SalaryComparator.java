package lab.entreprise.comparator;

import lab.entreprise.employe.Employe;

import java.util.Comparator;

public class SalaryComparator implements Comparator<Employe> {
    @Override
    public int compare(Employe o1, Employe o2) {
        return Double.compare(o1.getSalaire(), o2.getSalaire());
    }
}

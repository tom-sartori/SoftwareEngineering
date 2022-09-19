package lab.entreprise.comparator;

import lab.entreprise.employe.Employe;

import java.util.Comparator;

public class SalaryComparator implements Comparator<Employe> {

    private final ORDER order;

    public SalaryComparator() {
        this.order = ORDER.ASC;
    }

    public SalaryComparator(ORDER order) {
        this.order = order;
    }

    @Override
    public int compare(Employe o1, Employe o2) {
        return order.getValue() * Double.compare(o1.getSalaire(), o2.getSalaire());
    }
}

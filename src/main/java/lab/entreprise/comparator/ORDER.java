package lab.entreprise.comparator;


public enum ORDER {
    ASC(1),
    DESC(-1);

    private final int value;

    ORDER(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

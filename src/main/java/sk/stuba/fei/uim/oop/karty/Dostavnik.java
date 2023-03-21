package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.stol.Stol;

public class Dostavnik extends Karta{
    private static final String NAZOV_KARTY = "Dostavnik";

    public Dostavnik(Stol stol) {
        super(NAZOV_KARTY, stol);
    }
    public Dostavnik(String meno, Stol stol) {
        super(meno, stol);
    }

    @Override
    public boolean mozeHrat() {
        return true;
    }
}

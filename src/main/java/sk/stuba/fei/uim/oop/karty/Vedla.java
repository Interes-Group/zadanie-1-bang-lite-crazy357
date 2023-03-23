package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.stol.Stol;

public class Vedla extends Karta {
    private static final String  MENO_KARTY = "Vedla!";

    public Vedla(Stol stol) {
        super(MENO_KARTY, stol);
    }

    @Override
    public boolean mozeHrat() {
        return false;
    }
}
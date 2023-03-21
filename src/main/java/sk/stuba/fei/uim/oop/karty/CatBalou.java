package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.stol.Stol;

public class CatBalou extends Karta {
    private static final String NAZOV_KARTY = "Cal balou";

    public CatBalou(Stol stol) {
        super(NAZOV_KARTY, stol);
    }

    public CatBalou(String meno, Stol stol) {
        super(meno, stol);
    }

    @Override
    public boolean mozeHrat() {
        return true;
    }
}

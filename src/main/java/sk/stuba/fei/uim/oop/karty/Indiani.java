package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.stol.Stol;

public class Indiani extends Karta{
    private static final String NAZOV_KARTY = "Indiani";

    public Indiani(Stol stol) {
        super(NAZOV_KARTY, stol);
    }

    public Indiani(String meno, Stol stol) {
        super(meno, stol);
    }

    @Override
    public boolean mozeHrat() {
        return true;
    }
}

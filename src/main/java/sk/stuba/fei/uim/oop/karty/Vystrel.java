package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.stol.Stol;

public class Vystrel extends Karta{
    private static final String NAZOV_KARTY = "Bang!";

    public Vystrel(Stol stol) {
        super(NAZOV_KARTY, stol);
    }

    @Override
    public boolean mozeHrat() {
        return true;
    }

    @Override
    public void zahrajKartu(Hrac hrac) {
        super.zahrajKartu(hrac);
        hrac.odstranitZivot();
    }
}

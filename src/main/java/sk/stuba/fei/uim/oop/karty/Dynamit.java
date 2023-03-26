package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.stol.Stol;

public class Dynamit extends Karta {
    private static final String NAZOV_KARTY = "Dynamit";
    public Dynamit(Stol stol) {
        super(NAZOV_KARTY, stol);
    }

    @Override
    public boolean mozeHrat(Hrac hrac) {
        return true;
    }

    @Override
    public void zahrajKartu(Hrac hrac, Hrac[] hraci) {
        super.zahrajKartu(hrac, hraci);
        hrac.vylozitDynamitNaStol();
    }
}

package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.stol.Stol;

public class Barrel extends Karta {
    private static final String NAZOV_KARTY = "Barrel";

    public Barrel(Stol stol) {
        super(NAZOV_KARTY, stol);
    }

    @Override
    public boolean mozeHrat(Hrac hrac) {
        return (!hrac.skontrolovatBarrel(hrac));
    }

    @Override
    public void zahrajKartu(Hrac hrac, Hrac[] hraci) {
        super.zahrajKartu(hrac, hraci);
        hrac.vylozitBarrelNaStol();
    }
}

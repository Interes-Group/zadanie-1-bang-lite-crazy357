package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.stol.Stol;

public class Dostavnik extends Karta {
    private static final String NAZOV_KARTY = "Dostavnik";
    public Dostavnik(Stol stol) {
        super(NAZOV_KARTY, stol);
    }
    @Override
    public boolean mozeHrat() {
        return true;
    }
    @Override
    public void zahrajKartu(Hrac hrac, Hrac[] hraci) {
        super.zahrajKartu(hrac, hraci);
        hrac.potiahniDveKarty(this.stol.potiahniKarty());
    }
}
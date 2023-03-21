package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.bang.Bang;
import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.stol.Stol;

public class Vystrel extends Karta {
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

        Hrac ciel = Bang.vyberHraca();

        System.out.println("\n\n=== "+ciel.getMeno()+" stratil jeden zivot. ===\n\n");

        ciel.odstranitZivot();
    }
}

package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.stol.Stol;

public class Pivo extends Karta {
    private static final String MENO_KARTY = "Pivo";
    public Pivo(Stol stol) {
        super(MENO_KARTY, stol);
    }
    public Pivo(String meno, Stol stol) {
        super(meno, stol);
    }

    @Override
    public boolean mozeHrat() {
        return true;
    }

    @Override
    public void zahrajKartu(Hrac hrac) {
        super.zahrajKartu(hrac);

        System.out.println("Pridal si si jeden zivot.");
        hrac.pridatZivot();
        System.out.println("Pocet tvojich zivotov: " + hrac.getZivoty());
    }
}

package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.stol.Stol;

public class Pivo extends Karta {
    private static final String NAZOV_KARTY = "Pivo";

    public Pivo(Stol stol) {
        super(NAZOV_KARTY, stol);
    }

    @Override
    public boolean mozeHrat(Hrac hrac) {
        return true;
    }

    @Override
    public void zahrajKartu(Hrac hrac, Hrac[] hraci) {
        super.zahrajKartu(hrac, hraci);

        System.out.println("--- Pridal si si jeden zivot. ---");
        hrac.pridatZivot();
        System.out.println("--- Pocet tvojich zivotov je: " + hrac.getZivoty() + " ---\n");
    }
}
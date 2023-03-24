package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.stol.Stol;

public class Indiani extends Karta {
    private static final String NAZOV_KARTY = "Indiani";
    public Indiani(Stol stol) {
        super(NAZOV_KARTY, stol);
    }

    @Override
    public boolean mozeHrat(Hrac hrac) {
        return true;
    }

    @Override
    public void zahrajKartu(Hrac hrac, Hrac[] hraci) {
        super.zahrajKartu(hrac, hraci);
        int pocetZasiahnutych = 0;
        for (Hrac ciel : hraci) {
            if (ciel == hrac) {
                continue;
            }
            if (ciel.skontrolovatBang(ciel) > 0) {
                pocetZasiahnutych++;
            }
        }
        System.out.println("--- Podarilo sa ti zasiahnut " + pocetZasiahnutych + " protivnikov. ---\n");
    }
}
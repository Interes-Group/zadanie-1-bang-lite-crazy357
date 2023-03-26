package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.stol.Stol;

public class Dostavnik extends Karta {
    private static final String NAZOV_KARTY = "Dostavnik";

    public Dostavnik(Stol stol) {
        super(NAZOV_KARTY, stol);
    }

    @Override
    public boolean mozeHrat(Hrac hrac) {
        if (this.stol.getPocetKarietVOdhadzovacom() == 1 && this.stol.getPocetKarietVBalicku() == 1) {
            return true;
        } else {
            if (this.stol.getPocetKarietVBalicku() >= 2 || this.stol.getPocetKarietVOdhadzovacom() >= 2) {
                return true;
            }
            if (this.stol.getPocetKarietVBalicku() < 2) {
                return this.stol.getPocetKarietVOdhadzovacom() >= 2;
            }
        }
        return false;
    }

    @Override
    public void zahrajKartu(Hrac hrac, Hrac[] hraci) {
        super.zahrajKartu(hrac, hraci);
        int pocetKarietPred = hrac.ukazatKartyNaRuke().size();
        hrac.potiahniDveKarty(this.stol.potiahniKarty());
        int pocetKarietPo = hrac.ukazatKartyNaRuke().size();
        if ((pocetKarietPo - pocetKarietPred) == 0) {
            System.out.println("--- Nepotiahol si si ziadne karty. ---\n");
        }
    }
}
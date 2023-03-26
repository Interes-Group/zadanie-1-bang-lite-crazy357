package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.stol.Stol;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Vazenie extends Karta {
    private static final String NAZOV_KARTY = "Vazenie";

    public Vazenie(Stol stol) {
        super(NAZOV_KARTY, stol);
    }

    @Override
    public boolean mozeHrat(Hrac hrac) {
        return true;
    }

    @Override
    public void zahrajKartu(Hrac hrac, Hrac[] hraci) {
        super.zahrajKartu(hrac, hraci);
        vyberHraca(hraci, hrac);
    }

    private void vyberHraca(Hrac[] hraci, Hrac hrac) {
        ArrayList<Hrac> ciele = new ArrayList<>();
        for (Hrac hrac1 : hraci) {
            if (hrac1.jeAktivny()) {
                if (hrac1 != hrac) {
                    if (hrac1.skontrolovatVazenie() == 0) {
                        ciele.add(hrac1);
                    }
                }
            }
        }
        int cisloHraca = 0;
        if (ciele.size() != 0) {
            System.out.println("--- Hraci, ktorych mozes dat do vazenia: ---");
            for (int i = 0; i < ciele.size(); i++) {
                System.out.println("     Hrac " + (i + 1) + ": " + ciele.get(i).getMeno());
            }
            while (true) {
                cisloHraca = ZKlavesnice.readInt("--- Vyber cislo hraca, na ktoreho chces pouzit kartu: ---") - 1;
                if (cisloHraca < 0 || cisloHraca > ciele.size() - 1) {
                    System.out.println("!!! Zadal si nespravne cislo hraca, skus znova! !!!");
                } else break;
            }
            ciele.get(cisloHraca).vylozitVazenieNaStol();
            System.out.println("--- Dal si " + ciele.get(cisloHraca).getMeno() + " do vazenia! ---\n\n");
        } else {
            System.out.println("--- Tuto kartu nemozes teraz zahrat. ---");
            hrac.ukazatKartyNaRuke().add(new Vazenie(stol));
        }
    }
}

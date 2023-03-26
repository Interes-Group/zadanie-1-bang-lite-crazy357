package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.stol.Stol;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Vystrel extends Karta {
    private static final String NAZOV_KARTY = "Bang!";

    public Vystrel(Stol stol) {
        super(NAZOV_KARTY, stol);
    }

    @Override
    public boolean mozeHrat(Hrac hrac) {
        return true;
    }

    @Override
    public void zahrajKartu(Hrac hrac, Hrac[] hraci) {
        super.zahrajKartu(hrac, hraci);
        Hrac ciel = this.vybratHraca(hraci, hrac);
        int zivotDole;
        if (ciel.skontrolovatBarrel(ciel)) {
            zivotDole = (int) (Math.random() * 5);
            if (zivotDole == 0) {
                System.out.println("--- " + ciel.getMeno() + " sa skryl za Barrel. ---");
            } else {
                System.out.println("--- " + ciel.getMeno() + " sa neskryl za Barrel. ---");
                zivotDole = ciel.skontrolovatVedla(ciel);
            }
        } else {
            zivotDole = ciel.skontrolovatVedla(ciel);
        }
        if (zivotDole != 0) {
            System.out.println("\n=== " + ciel.getMeno() + " stratil jeden zivot. Jeho pocet zivotov je: " + ciel.getZivoty() + " ===\n");
        } else {
            System.out.println("--- " + ciel.getMeno() + " nestratil zivot, minul si ho. ---\n");
        }
    }

    private Hrac vybratHraca(Hrac[] hraci, Hrac hrac) {
        int aktualnyHrac = 0;
        for (Hrac hrac1 : hraci) {
            if (hrac1 != hrac) {
                aktualnyHrac++;
            } else break;
        }
        ArrayList<Hrac> zijuciHraci = getZijucichHracov(hraci, aktualnyHrac);
        System.out.println("--- Zijuci hraci: ---");
        for (int i = 0; i < zijuciHraci.size(); i++) {
            System.out.println("     Hrac " + (i + 1) + ": " + zijuciHraci.get(i).getMeno() + ". Pocet zivotov: " + zijuciHraci.get(i).getZivoty());
        }
        int cisloHraca;
        while (true) {
            cisloHraca = ZKlavesnice.readInt("--- Vyber cislo hraca, na ktoreho chces pouzit kartu: ---") - 1;
            if (cisloHraca < 0 || cisloHraca > zijuciHraci.size() - 1) {
                System.out.println("!!! Zadal si nespravne cislo hraca, skus znova! !!!");
            } else break;
        }
        return zijuciHraci.get(cisloHraca);
    }

    private ArrayList<Hrac> getZijucichHracov(Hrac[] hraci, int aktualnyHrac) {
        ArrayList<Hrac> zijuciHraci = new ArrayList<>();
        for (Hrac hrac : hraci) {
            if (hrac != hraci[aktualnyHrac]) {
                if (hrac.jeAktivny()) {
                    zijuciHraci.add(hrac);
                }
            }
        }
        return zijuciHraci;
    }
}
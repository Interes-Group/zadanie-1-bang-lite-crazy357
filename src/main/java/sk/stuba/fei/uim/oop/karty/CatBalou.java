package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.stol.Stol;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class CatBalou extends Karta {
    private static final String NAZOV_KARTY = "Cat Balou";

    public CatBalou(Stol stol) {
        super(NAZOV_KARTY, stol);
    }

    @Override
    public boolean mozeHrat(Hrac hrac) {
        return true;
    }

    @Override
    public void zahrajKartu(Hrac hrac, Hrac[] hraci) {
        super.zahrajKartu(hrac, hraci);
        ArrayList<Hrac> ciele = hraciKtoriMajuKarty(hrac, hraci);

        if (ciele.size() != 0) {
            Hrac vybrany = vyberHraca(hrac, ciele);
            int miesto = vyberMiesto(vybrany);

            Karta karta;
            if (miesto == 1) {
                karta = vybrany.ukazatKartyNaRuke().get((int) (Math.random() * (vybrany.ukazatKartyNaRuke().size())));
                vybrany.odstranitKartuZRuky(karta);
            } else {
                karta = vybrany.ukazatKartyNaStole().get((int) (Math.random() * (vybrany.ukazatKartyNaStole().size())));
                vybrany.odstranitKartuZoStola(karta);
            }
            this.stol.kartaDoOdhadzovaciehoBalika(karta);
            System.out.println("--- Zahodil si hracovi kartu " + karta.getMeno() + " ---\n");
        } else {
            System.out.println("!!! Tuto kartu teraz nemozes zahrat, ostatni nemaju ziadne karty. !!!\n");
            hrac.ukazatKartyNaRuke().add(new CatBalou(stol));
        }
    }

    private ArrayList<Hrac> hraciKtoriMajuKarty(Hrac hrac, Hrac[] hraci) {
        ArrayList<Hrac> ciele = new ArrayList<>();
        for (Hrac ciel : hraci) {
            if (ciel != hrac) {
                if (ciel.ukazatKartyNaRuke().size() != 0 || ciel.ukazatKartyNaStole().size() != 0) {
                    ciele.add(ciel);
                }
            }
        }
        return ciele;
    }

    private Hrac vyberHraca(Hrac hrac, ArrayList<Hrac> ciele) {
        System.out.println("--- Hraci, ktori maju karty na ruke alebo na stole: ---");
        int poradoveCislo = 1;
        for (int i = 0; i < ciele.size(); i++) {
            if (ciele.get(i) != hrac) {
                System.out.println("     Hrac " + poradoveCislo + ": " + ciele.get(i).getMeno());
                System.out.println("     --- Pocet kariet na ruke: " + ciele.get(i).ukazatKartyNaRuke().size());
                System.out.println("     --- Pocet kariet na stole: " + ciele.get(i).ukazatKartyNaStole().size());
                poradoveCislo++;
            }
        }
        int cisloHraca;
        while (true) {
            cisloHraca = ZKlavesnice.readInt("--- Vyber cislo hraca, ktoremu chces odhodit kartu: ---") - 1;
            if (cisloHraca < 0 || cisloHraca > ciele.size() - 1) {
                System.out.println("!!! Zadal si nespravne cislo hraca, skus znova! !!!");
            } else break;
        }
        return ciele.get(cisloHraca);
    }

    private int vyberMiesto(Hrac vybrany) {
        int miesto;
        while (true) {
            miesto = ZKlavesnice.readInt("--- Vyber miesto, odkial chces hracovi odhodit kartu: ---\n    (1) z ruky\n    (2) zo stola");
            if (miesto < 1 || miesto > 2) {
                System.out.println("!!! Zadal si nespravne cislo miesta, skus znova! !!!");
            } else {
                if (miesto == 2 && vybrany.ukazatKartyNaStole().size() == 0) {
                    System.out.println("!!! Tento hrac nema karty na stole, vyber ine miesto. !!!");
                } else if (miesto == 1 && vybrany.ukazatKartyNaRuke().size() == 0) {
                    System.out.println("!!! Tento hrac, nema karty na ruke, vyber ine miesto. !!!");
                } else break;
            }
        }
        return miesto;
    }

}
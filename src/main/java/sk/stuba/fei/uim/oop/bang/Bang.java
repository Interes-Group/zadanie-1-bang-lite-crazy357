package sk.stuba.fei.uim.oop.bang;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.karty.Barrel;
import sk.stuba.fei.uim.oop.karty.Dynamit;
import sk.stuba.fei.uim.oop.karty.Karta;
import sk.stuba.fei.uim.oop.stol.Stol;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Bang {
    private Hrac[] hraci;
    private Stol stol;
    private int aktualnyHrac;

    public Bang() {
        System.out.println("\n=== Vitaj v hre Bang! ===\n");
        inicializovatHracov();
        this.zacatHru();
    }

    private void inicializovatHracov() {
        int pocetHracov = 0;
        while (pocetHracov < 2 || pocetHracov > 4) {
            pocetHracov = ZKlavesnice.readInt("=== Zadaj pocet hracov 2-4: ===");
            if (pocetHracov < 2 || pocetHracov > 4) {
                System.out.println("!!! Zadal si nespravny pocet hracov, skus znova! !!!\n");
            }
        }
        this.hraci = new Hrac[pocetHracov];
        for (int i = 0; i < pocetHracov; i++) {
            this.hraci[i] = new Hrac(ZKlavesnice.readString("=== Zadaj meno " + (i + 1) + ". hraca: "));
        }
        this.stol = new Stol(this.hraci);
    }

    private void zacatHru() {
        System.out.println("\n\n\n========== HRA ZACALA ==========");
        int pocetKol = 0;
        Hrac predoslyHrac = null;
        while (this.getPocetHrajucichHracov() > 1) {
            System.out.println("\n\n\n");
            if (this.aktualnyHrac % hraci.length == 0) {
                pocetKol++;
                System.out.println("======== ZACINA " + pocetKol + ". KOLO. =========\n");
            }
            Hrac aktivnyHrac = this.hraci[this.aktualnyHrac];
            if (!aktivnyHrac.jeAktivny()) {
                aktivnyHrac.odstranitKarty();
                this.inkrementPocitadlo();
                continue;
            }
            System.out.println("=== " + aktivnyHrac.getMeno() + " zacina tah. ===");
            System.out.println("=== Tvoj pocet zivotov je: " + aktivnyHrac.getZivoty() + " ===\n");

            if(aktivnyHrac.skontrolovatDynamit() == 0) {
                this.spravTah(aktivnyHrac, predoslyHrac);
            }
            else {
                aktivnyHrac.skontrolovatEfetkDynamitu(predoslyHrac, aktivnyHrac);
                if (aktivnyHrac.getZivoty() > 0) {
                    this.spravTah(aktivnyHrac, predoslyHrac);
                }
            }
            predoslyHrac = aktivnyHrac;
            System.out.println("Predosly hrac je " + predoslyHrac.getMeno());
            this.inkrementPocitadlo();
        }
        System.out.println("\n\n========== HRA SKONCILA ==========");
        System.out.println("Vyherca je " + getVitaz().getMeno());
    }

    private void spravTah(Hrac aktivnyHrac, Hrac predoslyHrac) {
        aktivnyHrac.potiahniDveKarty(this.stol.potiahniKarty());
        ArrayList<Karta> hracieKarty;
        ArrayList<Karta> vsetkyKartyNaRuke;
        ArrayList<Karta> kartyNaStole;
        int pokracovat = 1;

        while (true) {
            hracieKarty = aktivnyHrac.ukazatHracieKarty();
            vsetkyKartyNaRuke = aktivnyHrac.ukazatKartyNaRuke();
            kartyNaStole = aktivnyHrac.ukazatKartyNaStole();

            if (hracieKarty.size() != 0 && pokracovat != 0) {
                if (kartyNaStole.size() != 0) {
                    System.out.println("--- Na stole mas tieto karty: ---");
                    for (int i = 0; i < kartyNaStole.size(); i++) {
                        System.out.println("     Karta " + (i + 1) + ": " + kartyNaStole.get(i).getMeno());
                    }
                } else {
                    System.out.println("--- Na stole nemas ziadne karty. ---");
                }
                System.out.println("--- Na ruke mas tieto karty: ---");
                for (int i = 0; i < vsetkyKartyNaRuke.size(); i++) {
                    System.out.println("     Karta " + (i + 1) + ": " + vsetkyKartyNaRuke.get(i).getMeno());
                }
                pokracovat = this.zahrajKartu(hracieKarty, aktivnyHrac);
            } else {
                if (hracieKarty.size() == 0) {
                    System.out.println("--- Nemas na ruke ziadne hratelne karty. ---");
                }
                System.out.println("=== Tvoj tah konci. ===");
                System.out.println("==================================================");
                if (vsetkyKartyNaRuke.size() > aktivnyHrac.getZivoty()) {
                    aktivnyHrac.odstranitPrebytocneKarty(stol);
                }
                break;
            }
            if (getPocetHrajucichHracov() == 1) {
                break;
            }
        }
    }

    private int zahrajKartu(ArrayList<Karta> hracieKarty, Hrac aktivnyHrac) {
        System.out.println("--- Karty, ktore mozes zahrat: ---");
        int cisloKarty = vyberKartu(hracieKarty);
        if (cisloKarty != 0) {
            cisloKarty--;
            hracieKarty.get(cisloKarty).zahrajKartu(aktivnyHrac, hraci);
            aktivnyHrac.odstranitKartuZRuky(this.stol.kartaDoOdhadzovaciehoBalika(hracieKarty.get(cisloKarty)));
            cisloKarty++;
        }
        return cisloKarty;
    }

    private int vyberKartu(ArrayList<Karta> hracieKarty) {
        for (int i = 0; i < hracieKarty.size(); i++) {
            System.out.println("     Karta " + (i + 1) + ": " + hracieKarty.get(i).getMeno());
        }
        int cisloKarty;
        while (true) {
            cisloKarty = ZKlavesnice.readInt("--- Zadaj cislo karty, ktoru chces zahrat, " +
                    "stlac 0 ak nechces zahrat ziadnu kartu: ---");
            if (cisloKarty < 0 || cisloKarty > hracieKarty.size()) {
                System.out.println("!!! Zadal si nespravne cislo, skus znova! !!!");
            } else break;
        }
        return cisloKarty;
    }

    private int getPocetHrajucichHracov() {
        int pocet = 0;
        for (Hrac hrac : this.hraci) {
            if (hrac.jeAktivny()) {
                pocet++;
            }
        }
        return pocet;
    }

    private void inkrementPocitadlo() {
        this.aktualnyHrac++;
        this.aktualnyHrac %= this.hraci.length;
    }

    private Hrac getVitaz() {
        for (Hrac hrac : this.hraci) {
            if (hrac.jeAktivny()) {
                return hrac;
            }
        }
        return null;
    }

}
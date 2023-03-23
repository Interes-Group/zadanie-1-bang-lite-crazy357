package sk.stuba.fei.uim.oop.bang;

import sk.stuba.fei.uim.oop.hrac.Hrac;
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
            this.hraci[i] = new Hrac(ZKlavesnice.readString("=== Zadaj meno " + (i+1) + ". hraca: "));
        }
        this.stol = new Stol(this.hraci);
    }
    private void zacatHru() {
        System.out.println("\n===== HRA ZACALA =====");
        while (this.getPocetHrajucichHracov() > 1) {
            Hrac aktivnyHrac = this.hraci[this.aktualnyHrac];
            if (!aktivnyHrac.jeAktivny()) {
                aktivnyHrac.odstranitKarty();
                this.inkrementPocitadlo();
                continue;
            }
            System.out.println("\n\n\n" + aktivnyHrac.getMeno() + " zacina tah. ===");
            System.out.println("=== Tvoj pocet zivotov je: " + aktivnyHrac.getZivoty() + " ===\n");
            this.spravTah(aktivnyHrac);
            this.inkrementPocitadlo();
        }
        System.out.println("=== HRA SKONCILA ===");
        System.out.println("Vyherca je " + getVitaz().getMeno());
    }
    private void spravTah(Hrac aktivnyHrac) {
        aktivnyHrac.potiahniDveKarty(this.stol.potiahniKarty());
        System.out.println("--- Potiahol si si dve karty. ---");
        ArrayList<Karta> hracieKarty;
        ArrayList<Karta> vsetkyKartyNaRuke;
        ArrayList<Karta> kartyNaStole = aktivnyHrac.ukazatKartyNaStole();
        int pokracovat = 1;
        while (true) {
            hracieKarty = aktivnyHrac.ukazatHracieKarty();
            vsetkyKartyNaRuke = aktivnyHrac.ukazatKartyNaRuke();
            if (hracieKarty.size() != 0 && pokracovat != 0) {
                System.out.println("--- Na ruke mas tieto karty: ---");

                for (int i = 0; i < vsetkyKartyNaRuke.size(); i++) {
                    System.out.println("     Karta " + (i+1) + ": " + vsetkyKartyNaRuke.get(i).getMeno());
                }
                pokracovat = this.zahrajKartu(hracieKarty, aktivnyHrac);
            }
            else {
                System.out.println("=== Tvoj tah konci. ===");
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
            System.out.println("     Karta " + (i+1) + ": " + hracieKarty.get(i).getMeno());
        }
        int cisloKarty;
        while (true) {
            cisloKarty = ZKlavesnice.readInt("--- Zadaj cislo karty, ktoru chces zahrat, " +
                    "stlac 0 ak nechces zahrat ziadnu kartu: ---");
            if (cisloKarty < 0 || cisloKarty > hracieKarty.size()) {
                System.out.println("!!! Zadal si nespravne cislo, skus znova! !!!");
            }
            else break;
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
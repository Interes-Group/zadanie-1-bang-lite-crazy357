package sk.stuba.fei.uim.oop.bang;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.karty.Karta;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Bang {
    private final Hrac[] hraci;
    private int aktualnyHrac;

    public Bang() {
        System.out.println("=== Vitaj v hre BANG! ===");
        int pocetHracov = 0;
        while (pocetHracov < 2 ||  pocetHracov > 4) {
            pocetHracov = ZKlavesnice.readInt("--- Zadaj pocet hracov(2-4): ---");
            if (pocetHracov < 2 || pocetHracov > 4) {
                System.out.println("!!! Zadal si nespravny pocet hracov, skus znova. !!!");
            }
        }
        this.hraci = new Hrac[pocetHracov];
        for (int i = 0; i < pocetHracov; i++) {
            this.hraci[i] = new Hrac(ZKlavesnice.readString("--- Zadaj meno "+ (i+1) +". hraca: ---"));
        }

        this.zacatHru();
    }

    private void zacatHru() {
        System.out.println("=== HRA ZACALA ===");
        while (this.getPocetHrajucichHracov() > 1) {
            Hrac aktivnyHrac = this.hraci[this.aktualnyHrac];
            if (!aktivnyHrac.jeAktivny()) {
                ArrayList<Karta> kartyDoDecku = aktivnyHrac.odstranitKartyZRuky();
                this.inkrementPocitadlo();
                continue;
            }

            System.out.println("--- Hrac "+ aktivnyHrac.getMeno() + " zacina tah ---");
            System.out.println("--- "+ aktivnyHrac.getMeno() + ", tvoj pocet zivotov je: "+ aktivnyHrac.getZivoty()+"---");
            this.spravTah(aktivnyHrac);
            this.inkrementPocitadlo();
        }
        System.out.println("=== HRA SKONCILA ===");
        System.out.println("A vyherca je "+ getVitaz().getMeno());
    }

    private void spravTah(Hrac aktivnyHrac) {
        ArrayList<Karta> hracieKarty = aktivnyHrac.ukazatHracieKarty();
        if (hracieKarty.size() != 0) {
            this.zahrajKartu(hracieKarty, aktivnyHrac);
        } else {
            this.odstranitKartu(aktivnyHrac);
        }
    }

    private void odstranitKartu(Hrac aktivnyHrac) {
        System.out.println("--- Uz nemas ziadne hratelne karty na ruke! Ktoru kartu chces zahodit? ---");
        ArrayList<Karta> karty = aktivnyHrac.ukazatVsetkyKarty();
        int cisloKarty = vyberKartu(karty, "odstranit");
        karty.get(cisloKarty).odstranitKartu(aktivnyHrac);
    }

    private void zahrajKartu(ArrayList<Karta> hracieKarty, Hrac aktivnyHrac) {
        System.out.println("--- Hracie karty na ruke ---");
        int cisloKarty = vyberKartu(hracieKarty, "zahrat");
        hracieKarty.get(cisloKarty).zahrajKartu(aktivnyHrac);
    }

    private int vyberKartu(ArrayList<Karta> karty, String sloveso) {
        for (int i = 0; i < karty.size(); i++) {
            System.out.println("Karta "+ (i+1)+ ":"+ karty.get(i).getMeno());
        }
        int cisloKarty = 0;
        while (true) {
            cisloKarty = ZKlavesnice.readInt("--- Zadaj cislo karty, ktoru chces "+ sloveso+": ---")-1;
            if (cisloKarty < 0 || cisloKarty > karty.size() - 1) {
                System.out.println("!!! Zadal si nespravne cislo karty, skus znova. !!!");
            } else {
                break;
            }
        }
        return cisloKarty;
    }

    private void inkrementPocitadlo() {
        this.aktualnyHrac++;
        this.aktualnyHrac %= this.hraci.length;
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

    private Hrac getVitaz() {
        for (Hrac hrac : this.hraci) {
            if (hrac.jeAktivny()) {
                return hrac;
            }
        }
        return null;
    }














}

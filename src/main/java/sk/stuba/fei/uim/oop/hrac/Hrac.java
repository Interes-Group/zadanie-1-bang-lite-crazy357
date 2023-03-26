package sk.stuba.fei.uim.oop.hrac;

import sk.stuba.fei.uim.oop.karty.*;
import sk.stuba.fei.uim.oop.stol.Stol;

import java.util.ArrayList;

public class Hrac {
    private final String meno;
    private int zivoty;
    private ArrayList<Karta> kartyNaRuke;
    private ArrayList<Karta> kartyNaStole;
    protected Stol stol;

    public Hrac(String meno) {
        this.kartyNaRuke = new ArrayList<>();
        this.kartyNaStole = new ArrayList<>();
        this.meno = meno;
        this.zivoty = 4;
    }

    public String getMeno() {
        return meno;
    }

    public int getZivoty() {
        return zivoty;
    }

    public int skontrolovatVedla(Hrac ciel) {
        int podariloSa = 1;
        for (Karta karta : ciel.kartyNaRuke) {
            if (karta instanceof Vedla) {
                System.out.println("--- " + ciel.getMeno() + " pouzil kartu Vedla! ---");
                ciel.odstranitKartuZRuky(karta);
                podariloSa = 0;
                break;
            }
        }
        if (podariloSa == 1) {
            ciel.zivoty--;
        }
        return podariloSa;
    }

    public void pridatZivot() {
        this.zivoty++;
    }

    public void setKarty(ArrayList<Karta> noveKarty) {
        this.kartyNaRuke = noveKarty;
    }

    public boolean jeAktivny() {
        return this.zivoty > 0;
    }

    public ArrayList<Karta> ukazatKartyNaRuke() {
        return this.kartyNaRuke;
    }

    public ArrayList<Karta> ukazatKartyNaStole() {
        return this.kartyNaStole;
    }

    public ArrayList<Karta> ukazatHracieKarty() {
        ArrayList<Karta> karty = new ArrayList<>();
        for (Karta karta : this.kartyNaRuke) {
            if (karta.mozeHrat(this)) {
                karty.add(karta);
            }
        }
        return karty;
    }

    public void potiahniDveKarty(ArrayList<Karta> potiahniKarty) {
        this.kartyNaRuke.addAll(potiahniKarty);
    }

    public void odstranitKartuZRuky(Karta karta) {
        this.kartyNaRuke.remove(karta);
    }

    public void odstranitPrebytocneKarty(Stol stol) {
        int pocetKarietNaOdstranenie = this.kartyNaRuke.size() - this.getZivoty();
        for (int i = 0; i < pocetKarietNaOdstranenie; i++) {
            Karta karta = this.kartyNaRuke.get((int) (Math.random() * this.kartyNaRuke.size()));
            this.odstranitKartuZRuky(karta);
            this.stol = stol;
            this.stol.kartaDoOdhadzovaciehoBalika(karta);
            System.out.println("--- Odstranena prebytocna karta: " + karta.getMeno() + " ---");

        }
        System.out.println("==================================================");
    }

    public void odstranitKarty() {
        ArrayList<Karta> kartyDoBalicka = new ArrayList<>();
        kartyDoBalicka.addAll(this.kartyNaStole);
        kartyDoBalicka.addAll(this.kartyNaRuke);
        this.kartyNaRuke.clear();
        this.kartyNaStole.clear();
        if (kartyDoBalicka.size() != 0) {
            this.stol.kartyDoOdhadzovaciehoBalika(kartyDoBalicka);
        }
    }

    public int skontrolovatBang(Hrac hrac) {
        int trafil = 1;
        for (Karta karta : hrac.kartyNaRuke) {
            if (karta instanceof Vystrel) {
                System.out.println("--- " + hrac.getMeno() + " zahral kartu Bang! na indianov. ---");
                hrac.odstranitKartuZRuky(karta);
                trafil = 0;
                break;
            }
        }
        if (trafil != 0) {
            hrac.zivoty--;
            System.out.println("--- " + hrac.getMeno() + " nema kartu Bang! na ruke. Stratil jeden zivot. Pocet zivotov: " + hrac.getZivoty() + " ---");
        }
        return trafil;
    }

    public boolean skontrolovatBarrel(Hrac hrac) {
        for (Karta karta : hrac.kartyNaStole) {
            if (karta instanceof Barrel) {
                return true;
            }
        }
        return false;
    }

    public void odstranitKartuZoStola(Karta karta) {
        this.ukazatKartyNaStole().remove(karta);
    }

    public void skontrolovatEfetkDynamitu(Hrac predoslyHrac, Hrac hrac) {
        System.out.println("--- Kontrola efektu dynamitu: ---");
        int zivotyDole = (int) (Math.random() * 9);
        Karta karta = null;
        for (Karta hodnota : hrac.kartyNaStole) {
            if (hodnota instanceof Dynamit) {
                karta = hodnota;
                break;
            }
        }
        if (zivotyDole != 0) {
            System.out.println("--- Dynamit ti nevybuchol, posuva sa predoslemu hracovi. ---\n");
            hrac.odstranitKartuZoStola(karta);
            predoslyHrac.kartyNaStole.add(new Dynamit(stol));
        } else {
            System.out.println("--- Dynamit ti vybuchol, stracas 3 zivoty. ---");
            if (hrac.zivoty > 3) {
                for (int i = 0; i < 3; i++) {
                    hrac.zivoty--;
                }
            } else {
                for (int i = 0; i < hrac.zivoty; i++) {
                    hrac.zivoty--;
                }
                System.out.println("--- Stratil si vsetky zivoty. ----\n");
            }
            hrac.odstranitKartuZoStola(karta);
            hrac.stol.kartaDoOdhadzovaciehoBalika(karta);
        }
    }

    public void vylozitBarrelNaStol() {
        for (Karta karta : this.kartyNaRuke) {
            if (karta instanceof Barrel) {
                this.kartyNaStole.add(karta);
                this.odstranitKartuZRuky(karta);
                break;
            }
        }
    }

    public void vylozitDynamitNaStol() {
        for (Karta karta : this.kartyNaRuke) {
            if (karta instanceof Dynamit) {
                this.kartyNaStole.add(karta);
                this.odstranitKartuZRuky(karta);
                break;
            }
        }
    }

    public int skontrolovatDynamit() {
        for (Karta karta : this.kartyNaStole) {
            if (karta instanceof Dynamit) {
                return 1;
            }
        }
        return 0;
    }
}

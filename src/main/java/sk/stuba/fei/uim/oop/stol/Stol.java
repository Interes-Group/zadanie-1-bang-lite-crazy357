package sk.stuba.fei.uim.oop.stol;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.karty.*;

import java.util.ArrayList;
import java.util.Collections;

public class Stol {
    private ArrayList<Karta> balicek;
    private ArrayList<Karta> odhadzovaciBalicek;

    public Stol(Hrac[] hraci) {
        this.balicek = new ArrayList<>();
        this.odhadzovaciBalicek = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            balicek.add(new Vystrel(this));
        }
        for (int i = 0; i < 15; i++) {
            balicek.add(new Vedla(this));
        }
        for (int i = 0; i < 8; i++) {
            balicek.add(new Pivo(this));
        }
        for (int i = 0; i < 6; i++) {
            balicek.add(new CatBalou(this));
        }
        balicek.add(new Indiani(this));
        balicek.add(new Indiani(this));
        balicek.add(new Dostavnik(this));
        balicek.add(new Dostavnik(this));
        balicek.add(new Dostavnik(this));
        balicek.add(new Dostavnik(this));
        balicek.add(new Barrel(this));
        balicek.add(new Barrel(this));
        balicek.add(new Dynamit(this));

        Collections.shuffle(this.balicek);

        for (Hrac hrac : hraci) {
            ArrayList<Karta> noveKarty = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                noveKarty.add(balicek.remove(0));
            }
            hrac.setKarty(noveKarty);
        }
    }

    public int getPocetKarietVBalicku() {
        return this.balicek.size();
    }

    public int getPocetKarietVOdhadzovacom() {
        return this.odhadzovaciBalicek.size();
    }

    public void premiesatBalicek() {
        Collections.shuffle(this.odhadzovaciBalicek);
        this.balicek.addAll(this.odhadzovaciBalicek);
        odhadzovaciBalicek.clear();
    }

    public ArrayList<Karta> potiahniKarty() {
        ArrayList<Karta> karty = new ArrayList<>();
        if (this.balicek.size() == 1 && this.odhadzovaciBalicek.size() == 1) {
            this.premiesatBalicek();
            karty.add(balicek.remove(0));
            karty.add(balicek.remove(0));
            System.out.println("--- Potiahol si si dve karty. ---");
        } else {
            if (this.balicek.size() >= 2) {
                karty.add(balicek.remove(0));
                karty.add(balicek.remove(0));
                System.out.println("--- Potiahol si si dve karty. ---");
            } else {
                if (this.odhadzovaciBalicek.size() < 2) {
                    System.out.println("=== V balicku uz nie su ziadne karty. ===");
                } else {
                    this.premiesatBalicek();
                    karty.add(balicek.remove(0));
                    karty.add(balicek.remove(0));
                    System.out.println("--- Potiahol si si dve karty. ---");
                }
            }
        }
        return karty;
    }

    public Karta kartaDoOdhadzovaciehoBalika(Karta karta) {
        odhadzovaciBalicek.add(karta);
        return karta;
    }

    public void kartyDoOdhadzovaciehoBalika(ArrayList<Karta> karty) {
        odhadzovaciBalicek.addAll(karty);
    }
}
package sk.stuba.fei.uim.oop.stol;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.karty.Karta;
import sk.stuba.fei.uim.oop.karty.Pivo;
import sk.stuba.fei.uim.oop.karty.Vedla;
import sk.stuba.fei.uim.oop.karty.Vystrel;

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

        Collections.shuffle(this.balicek);

        for (Hrac hrac : hraci) {
            ArrayList<Karta> noveKarty = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                noveKarty.add(balicek.remove(0));
            }
            hrac.setKarty(noveKarty);
        }
    }
    public ArrayList<Karta> potiahniKarty() {
        ArrayList<Karta> karty = new ArrayList<>();
        karty.add(balicek.remove(0));
        karty.add(balicek.remove(0));
        return karty;
    }

    public Karta kartaDoOdhadzovaciehoBalika(Karta karta) {
        odhadzovaciBalicek.add(karta);
        return karta;
    }
}
package sk.stuba.fei.uim.oop.stol;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.karty.Karta;
import sk.stuba.fei.uim.oop.karty.Vedla;
import sk.stuba.fei.uim.oop.karty.Vystrel;

import java.util.ArrayList;
import java.util.Collections;

public class Stol {

    public Stol(Hrac[] hraci) {
        ArrayList<Karta> balicek = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            balicek.add(new Vystrel(this));
        }
        for (int i = 0; i < 15; i++) {
            balicek.add(new Vedla(this));
        }

        Collections.shuffle(balicek);
        for (Hrac hrac : hraci) {
            ArrayList<Karta> noveKarty = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                noveKarty.add(balicek.remove(0));
            }
            hrac.setKarty(noveKarty);
        }
    }
}

package sk.stuba.fei.uim.oop.stol;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.karty.*;

import java.util.ArrayList;
import java.util.Collections;

public class Stol {
    public static ArrayList<Karta> balicek;
    public static ArrayList<Karta> odhadzovaciBalicek;

    public Stol(Hrac[] hraci) {
        this.balicek = new ArrayList<>();
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
        for (int i = 0; i < 4; i++) {
            balicek.add(new Dostavnik(this));
        }
        balicek.add(new Indiani(this));
        balicek.add(new Indiani(this));


        Collections.shuffle(this.balicek);
        for (Hrac hrac : hraci) {
            ArrayList<Karta> noveKarty = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                noveKarty.add(balicek.remove(0));
            }
            hrac.setKarty(noveKarty);
        }

        System.out.println("karty v balicku:");
        for (Karta karta : balicek) {
            System.out.println(karta.getMeno());
        }
    }
}

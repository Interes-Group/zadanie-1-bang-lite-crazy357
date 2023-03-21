package sk.stuba.fei.uim.oop.hrac;

import sk.stuba.fei.uim.oop.karty.Karta;
import java.util.ArrayList;

public class Hrac {
    private final String meno;
    private int zivoty;
    private ArrayList<Karta> karty;

    public Hrac(String meno) {
        this.karty = new ArrayList<>();
        this.meno = meno;
        this.zivoty = 4;
    }

    public String getMeno() {
        return meno;
    }

    public int getZivoty() {
        return zivoty;
    }

    public void setKarty(ArrayList<Karta> karty) {
        this.karty = karty;
    }

    public ArrayList<Karta> odstranitKartyZRuky() {
        ArrayList<Karta> odstraneneKarty = new ArrayList<>(this.karty);
        this.karty.clear();
        return odstraneneKarty;
    }

    public ArrayList<Karta> ukazatHracieKarty() {
        ArrayList<Karta> karty = new ArrayList<>();
        for (Karta karta : this.karty) {
            if (karta.mozeHrat()) {
                karty.add(karta);
            }
        }
        return karty;
    }

    /*public ArrayList<Karta> ukazatVsetkyKarty() {
        return this.karty;
    }*/

    public boolean jeAktivny() {
        return this.zivoty > 0;
    }

    public void odstranitZivot() {
        this.zivoty--;
    }

}

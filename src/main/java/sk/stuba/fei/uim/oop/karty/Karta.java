package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;

import java.util.HashMap;

public abstract class Karta {
    protected String meno;

    public Karta(String meno) {
        this.meno = meno;
    }

    public abstract boolean zahrajKartu();

    public abstract boolean zahrajKartu(int index);

    public void zahrajKartu(Hrac hrac) {
        System.out.println("--- "+ hrac.getMeno() + " vyber " + this.meno + " kartu zahrat. ---");
    }

    public void odstranitKartu(Hrac hrac) {

    }

    public String getMeno() {
        return  meno;
    }
}

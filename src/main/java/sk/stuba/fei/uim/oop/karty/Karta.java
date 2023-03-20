package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.stol.Stol;

import java.util.HashMap;

public abstract class Karta {
    protected String meno;
    protected Stol stol;

    public Karta(String meno, Stol stol) {
        this.meno = meno;
        this.stol = stol;
    }

    public void zahrajKartu(Hrac hrac) {
        System.out.println("--- "+ hrac.getMeno() + " vyber " + this.meno + " kartu zahrat. ---");
    }


    public String getMeno() {
        return  meno;
    }
}
